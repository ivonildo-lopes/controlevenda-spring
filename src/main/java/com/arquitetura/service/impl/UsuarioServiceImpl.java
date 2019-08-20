package com.arquitetura.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.arquitetura.DTO.UsuarioDto;
import com.arquitetura.dao.UsuarioDao;
import com.arquitetura.error.AuthorizationException;
import com.arquitetura.error.BadValueException;
import com.arquitetura.model.Usuario;
import com.arquitetura.model.enums.Perfil;
import com.arquitetura.security.UserSpringSecurity;
import com.arquitetura.service.EmailService;
//import com.arquitetura.repository.UsuarioRepository;
import com.arquitetura.service.UsuarioService;

@Service(value = "usuarioService")
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioDao dao;
	
	@Autowired
	private EmailService emailService;
	
//	@Autowired
//	private UsuarioRepository repository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public Usuario findById(Long id) {
		
		UserSpringSecurity userss = UsuarioLogadoService.authenticaded();
		
		if(Objects.isNull(userss) || userss.nonHasRole(Perfil.ADMIN) && !id.equals(userss.getId())) {
			 throw new AuthorizationException("Acesso Negado, caso queira acesso solicite");
		}

		if (Objects.isNull(id)) {
			throw new BadValueException("O ID da usuario precisa ser informado");
		}

		Usuario obj = this.dao.findById(id).orElse(null);

		if (Objects.isNull(obj)) {
			throw new BadValueException("Não existe usuario com esse Id");
		}

		return obj;
	}
	
	@Override
	public Usuario findByUser(String user) {
		return this.dao.findByUser(user).orElse(null);
	}
	
	@Override
	public List<Usuario> findAll() {
		
		List<Usuario> usuarios = this.dao.findAll();
		
		if(Objects.isNull(usuarios)) {
			throw new BadValueException("Não existe nenhum usuario cadastrada");
		}

		return usuarios;
	}

	@Override
	public UsuarioDto save(UsuarioDto usuarioDto) {
		
		if(Objects.isNull(usuarioDto)) {
			throw new BadValueException("Informe os dados do usuario");
		}

		Usuario usuario = new Usuario();
		usuario.setUser(usuarioDto.getUser());
		usuario.setPassword(bCryptPasswordEncoder.encode(usuarioDto.getPassword()));
		usuario.setAtivo(usuarioDto.isAtivo());
		usuario.addPerfil(Perfil.FUNCIONARIO);
		if(Objects.nonNull(usuarioDto.getPerfis()))
			usuario.addPerfils(usuarioDto.getListaPerfis());
		
		
		if(Objects.isNull(this.dao.save(usuario))) {
			throw new BadValueException("Não foi possivel adicionar um usuario");
		}
		
		this.emailService.sendConfirmationEmailNewUser(usuario);
		
		return this.convertModelToDto(usuario);
	}
	
	@Override
	public UsuarioDto alterar(Long id, UsuarioDto usuarioDto) {
		
		if (Objects.isNull(id)) {
			throw new BadValueException("Informe o usuario para ser alterado");
		}
		
		Usuario usuario = this.findById(id);
		usuario.setUser(usuarioDto.getUser());
		usuario.setPassword(usuarioDto.getPassword());
		usuario.setAtivo(usuarioDto.isAtivo());
		if(Objects.nonNull(usuarioDto.getPerfis()))
			usuario.addPerfils(usuarioDto.getListaPerfis());
		
		this.dao.saveAndFlush(usuario);
		
		return this.convertModelToDto(usuario);
	}
	
	private UsuarioDto convertModelToDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setUser(usuario.getUser());
		usuarioDto.setPassword(usuario.getPassword());
		usuarioDto.setAtivo(usuario.isAtivo());
		usuarioDto.setPerfis(usuario.getPerfis());
		
		return usuarioDto;
	}
	
	private Usuario dtoToModel(UsuarioDto dto) {
		Usuario usuario = new Usuario();
		usuario.setId(dto.getId());
		usuario.setUser(dto.getUser());
		usuario.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
		usuario.setAtivo(dto.isAtivo());
		
		return usuario;
	}
	
	@Override
	public List<UsuarioDto> findByUsuarios(UsuarioDto dto) {
		return null; //this.repository.findByUsuarios(dto);
	}

}
