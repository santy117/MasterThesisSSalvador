package com.master.demo.service.impl;

import com.master.demo.Entities.Notificacion;
import com.master.demo.Repositories.NotificacionRepository;
import com.master.demo.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    private NotificacionRepository notificacionRepository;

    @Autowired
    public NotificacionServiceImpl(NotificacionRepository notificacionRepository){
        this.notificacionRepository = notificacionRepository;
    }
    @Override
    public void insertNotificacion(Notificacion notificacion) {
        //this.notificacionRepository.save(notificacion);
    }
}
