package com.master.demo.service.impl;

import com.example.models.ObjectResponseDTO;
import com.example.models.PartidaResponseDTO;
import com.master.demo.Entities.Objeto;
import com.master.demo.Entities.Partida;
import com.master.demo.Entities.PartidaKafka;
import com.master.demo.Entities.Version;
import com.master.demo.Kafka.KafkaProducer;
import com.master.demo.Repositories.PartidaRepository;
import com.master.demo.Repositories.VersionRepository;
import com.master.demo.service.PartidaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaServiceImpl implements PartidaService {

    private final PartidaRepository partidaRepository;
    private final VersionRepository versionRepository;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public PartidaServiceImpl(final PartidaRepository partidaRepository, final VersionRepository versionRepository, final KafkaProducer kafkaProducer){
        this.partidaRepository = partidaRepository;
        this.versionRepository = versionRepository;
        this.kafkaProducer = kafkaProducer;
    }

    @Override
    @Transactional
    public void insertarPartida(Integer idVersion, String gastos, String informacion) {
        Partida partida = new Partida();
        Optional<Version> version = this.versionRepository.findById(idVersion);
        partida.setVersion(version.get());
        partida.setGastos(gastos);
        partida.setInformacion(informacion);
        this.partidaRepository.save(partida);
    }

    @Override
    public PartidaResponseDTO getPartidaByIdPartida(Integer partidaId) {
        Optional<Partida> partida = this.partidaRepository.findById(partidaId);
        PartidaResponseDTO partidaResponseDTO = new PartidaResponseDTO();
        partidaResponseDTO.setIdPartida(partida.get().getIdPartida());
        partidaResponseDTO.setIdVersion(partida.get().getVersion().getIdVersion());
        partidaResponseDTO.setGastos(partida.get().getGastos());
        partidaResponseDTO.setInformacion(partida.get().getInformacion());
        return partidaResponseDTO;
    }

    @Override
    public List<PartidaResponseDTO> getPartidasByIdVersion(Integer versionId) {
        List<PartidaResponseDTO> partidasResponseDTO = new ArrayList<>();
        List<Partida> objetos = this.partidaRepository.findPartidaByIdVersion(versionId);
        objetos.forEach(objeto -> {
            PartidaResponseDTO partidaResponseDTO = new PartidaResponseDTO();
            partidaResponseDTO.setIdPartida(objeto.getIdPartida());
            partidaResponseDTO.setIdVersion(objeto.getVersion().getIdVersion());
            partidaResponseDTO.setGastos(objeto.getGastos());
            partidaResponseDTO.setInformacion(objeto.getInformacion());
            partidasResponseDTO.add(partidaResponseDTO);
        });
        return partidasResponseDTO;
    }

    @Override
    public void importarPartidas(MultipartFile file) {
        try{
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            List<PartidaKafka> listaPartidas = new ArrayList<PartidaKafka>();
            while (iterator.hasNext()) {
                PartidaKafka partida = new PartidaKafka();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                if(currentRow.getRowNum()>0){
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        int columna =  currentCell.getAddress().getColumn();
                        switch (columna){
                            case 0:
                                int idVersion = (int) currentCell.getNumericCellValue();
                                partida.setIdVersion(idVersion);
                                break;

                            case 1:
                                String gastos = currentCell.getStringCellValue();
                                partida.setGastos(gastos);
                                break;

                            case 2:
                                String informacion = currentCell.getStringCellValue();
                                partida.setInformacion(informacion);
                                break;
                        }


                    }
                    listaPartidas.add(partida);
                }

            }
            listaPartidas.forEach(partida -> {
                System.out.println("Partida: "+partida.getIdVersion()+ " - "+partida.getGastos()+" - "+partida.getInformacion());

            });
            this.kafkaProducer.insertPartidas(listaPartidas);
        }catch(final IOException e){
            e.printStackTrace();
        }

    }
}
