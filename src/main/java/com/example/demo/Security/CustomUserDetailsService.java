package com.example.demo.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.Entity.Admin;
import com.example.demo.Entity.AsesorInterno;
import com.example.demo.Entity.Estudiante;
import com.example.demo.Repository.AdminRepository;
import com.example.demo.Repository.AsesorIntRepository;
import com.example.demo.Repository.EstudianteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EstudianteRepository estudianteRepo;
    @Autowired
    private AsesorIntRepository asesorRepo;
    @Autowired
    private AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Buscar en estudiantes
        Optional<Estudiante> estudiante = estudianteRepo.findByNombre(username);
        if (estudiante.isPresent()) {
            return User.withUsername(estudiante.get().getNombre())
                       .password(estudiante.get().getContraseña())
                       .roles("ESTUDIANTE")
                       .build();
        }

        // Buscar en asesores
        Optional<AsesorInterno> asesor = asesorRepo.findByNombre(username);
        if (asesor.isPresent()) {
            return User.withUsername(asesor.get().getNombre())
                       .password(asesor.get().getContraseña())
                       .roles("ASESOR")
                       .build();
        }

        // Usuario fijo: administrador
        Optional<Admin> admin = adminRepo.findByNombre(username);
        if (admin.isPresent()) {
            return User.withUsername(admin.get().getNombre())
                       .password(admin.get().getContraseña())
                       .roles("ADMIN")
                       .build();
        }

        throw new UsernameNotFoundException("Usuario no encontrado: " + username);
    }
}
