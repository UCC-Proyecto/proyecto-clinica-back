package com.ucc.clinica.service;

import com.ucc.clinica.dto.request.RegisterRequest;
import com.ucc.clinica.entity.Usuario;
import com.ucc.clinica.exception.EmailDuplicadoException;
import com.ucc.clinica.repository.UsuarioRepository;
import com.ucc.clinica.service.impl.UsuarioServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceImplTest {
    @Mock
    private UsuarioRepository usuarioRepository;
    @Mock
    private PasswordEncoder encoder;
    @InjectMocks
    private UsuarioServiceImpl usuarioService;

    @Test
    void deberiaLanzarExcepcionCuandoEmailExiste(){
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("test@mail.com");

        when(usuarioRepository.existsUsuarioByEmail("test@mail.com")).thenReturn(true);

        assertThrows(
                EmailDuplicadoException.class,
                () -> usuarioService.registrarUsuario(registerRequest)
        );
    }

    @Test
    void deberiaRegistrarUsuarioCorrectamente() {

        RegisterRequest request = new RegisterRequest();
        request.setNombre("Juan");
        request.setApellido("Perez");
        request.setEmail("juan@gmail.com");
        request.setPassword("123456");

        when(usuarioRepository.existsUsuarioByEmail(
                "juan@gmail.com"))
                .thenReturn(false);

        when(encoder.encode("123456"))
                .thenReturn("password-encriptado");

        Usuario usuario = Usuario.builder()
                .id(1L)
                .nombre("Juan")
                .apellido("Perez")
                .email("juan@gmail.com")
                .password("password-encriptado")
                .build();

        when(usuarioRepository.save(org.mockito.ArgumentMatchers.any()))
                .thenReturn(usuario);

        var response = usuarioService.registrarUsuario(request);

        org.junit.jupiter.api.Assertions
                .assertEquals(
                        "juan@gmail.com",
                        response.getEmail());
    }

}
