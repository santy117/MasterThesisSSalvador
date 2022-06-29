package com.master.demo.service.impl;

import com.example.models.PartidaResponseDTO;
import com.master.demo.Entities.*;
import com.master.demo.Kafka.KafkaProducer;
import com.master.demo.Repositories.*;
import com.master.demo.service.PartidaService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

    private static final String USUARIO= "santiMOCK";


    private final PartidaRepository partidaRepository;
    private final VersionRepository versionRepository;
    private final ObjetoRepository objetoRepository;
    private final NotificacionRepository notificacionRepository;
    private final RegistroPeticionRepository registroPeticionRepository;
    private final KafkaProducer kafkaProducer;

    @Autowired
    public PartidaServiceImpl(final PartidaRepository partidaRepository, final VersionRepository versionRepository, ObjetoRepository objetoRepository,
                              RegistroPeticionRepository registroPeticionRepository, final KafkaProducer kafkaProducer, final NotificacionRepository notificacionRepository){
        this.partidaRepository = partidaRepository;
        this.versionRepository = versionRepository;
        this.objetoRepository = objetoRepository;
        this.registroPeticionRepository = registroPeticionRepository;
        this.kafkaProducer = kafkaProducer;
        this.notificacionRepository = notificacionRepository;
    }

    @Override
    @Transactional
    public void insertarPartida(Integer idVersion, String gastos, String informacion) {
        Partida partida = new Partida();
        Optional<Version> version = this.versionRepository.findById(idVersion);
        partida.setVersion(version.get());
        partida.setGastos(gastos);
        partida.setInformacion(informacion);

        Notificacion notificacion = new Notificacion();
        notificacion.setPartida(partida);
        notificacion.setUsuario(USUARIO);

        try{
            Partida partidaInsertada = this.partidaRepository.save(partida);
            //borramos partida para tests jmeter
            this.partidaRepository.deleteById(partidaInsertada.getIdPartida());
            System.out.println("Insertando partida: " + partidaInsertada.getIdPartida());
            notificacion.setMensaje("Partida insertada correctamente. Fecha: "+ java.time.LocalDate.now());
        }catch (Exception e){
            notificacion.setMensaje("Partida insertada incorrectamente. Fecha: "+ java.time.LocalDate.now());
        }
       // this.kafkaProducer.insertNotificacion(notificacion);


    }

    @Override
    public PartidaResponseDTO getPartidaByIdPartida(Integer partidaId, String user) {
        Optional<Partida> partida = this.partidaRepository.findById(partidaId);
        PartidaResponseDTO partidaResponseDTO = new PartidaResponseDTO();
        partidaResponseDTO.setIdPartida(partida.get().getIdPartida());
        partidaResponseDTO.setIdVersion(partida.get().getVersion().getIdVersion());
        partidaResponseDTO.setGastos(partida.get().getGastos());
        partidaResponseDTO.setInformacion(partida.get().getInformacion());

        return partidaResponseDTO;
    }

    @Override
    @Cacheable("partidas")
    @Transactional
    public List<PartidaResponseDTO> getPartidasByIdVersion(Integer versionId, String user) {
        List<PartidaResponseDTO> partidasResponseDTO = new ArrayList<>();
        List<Partida> objetos = this.partidaRepository.findPartidaByIdVersion(versionId);
        objetos.forEach(objeto -> {
            Notificacion notificacion = this.notificacionRepository.findByIdPartida(objeto.getIdPartida());
            PartidaResponseDTO partidaResponseDTO = new PartidaResponseDTO();
            if(notificacion != null){
                partidaResponseDTO.setUsuario(notificacion.getUsuario());
            }
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
    @Override
    public void importarPartidasSync(MultipartFile file) {
        try{
            Workbook workbook = new XSSFWorkbook(file.getInputStream());
            Sheet datatypeSheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = datatypeSheet.iterator();
            List<Partida> listaPartidas = new ArrayList<Partida>();
            while (iterator.hasNext()) {
                Partida partida = new Partida();
                Row currentRow = iterator.next();
                Iterator<Cell> cellIterator = currentRow.iterator();
                if(currentRow.getRowNum()>0){
                    while (cellIterator.hasNext()) {
                        Cell currentCell = cellIterator.next();
                        int columna =  currentCell.getAddress().getColumn();
                        switch (columna){
                            case 0:
                                int idVersion = (int) currentCell.getNumericCellValue();
                                Optional<Version> version = this.versionRepository.findById(idVersion);
                                partida.setVersion(version.get());
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
                this.insertarPartida(partida.getVersion().getIdVersion(), partida.getGastos(), partida.getInformacion());
            });

        }catch(final IOException e){
            e.printStackTrace();
        }

    }
    @Override
    public void registroPeticiones(String tipoRegistro, Integer idVersion, String user){
        Objeto objeto = this.objetoRepository.findByIdVersion(idVersion);
        Optional<Version> version = this.versionRepository.findById(idVersion);
        RegistroPeticion registroPeticion = new RegistroPeticion();
        registroPeticion.setTipoPeticion(tipoRegistro);
        registroPeticion.setUsuario(user);
        registroPeticion.setVersion(version.get());
        registroPeticion.setObjeto(objeto);
        this.registroPeticionRepository.save(registroPeticion);
    }

    @Override
    public void cachePartidasByIdVersionAsync(Integer versionId, String user) {
        LecturaPartida lecturaPartida = new LecturaPartida(versionId, user);
        this.kafkaProducer.lecturaPartida(lecturaPartida);
    }

}
