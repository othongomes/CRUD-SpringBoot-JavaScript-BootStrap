package br.com.springboot.curso_jdev_treinamento.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.springboot.curso_jdev_treinamento.model.Usuario;
import br.com.springboot.curso_jdev_treinamento.repository.UsuarioRepository;


@RestController
public class GreetingsController {
	
	@Autowired /*Injeção de dependência*/
	private UsuarioRepository usuarioRepository;
	
	
//    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
//    @ResponseStatus(HttpStatus.OK)
//    public String greetingText(@PathVariable String name) {
//	
//        return "Hello " + name + "!";
//    }
    
    @GetMapping(value = "listatodos")
    @ResponseBody /*Retorna os dados para o corpo da resposta*/
    public ResponseEntity<List<Usuario>> listaUsuario(){
    	
    	List<Usuario> usuarios = usuarioRepository.findAll(); /*Executa a consulta no banco de dados*/
    	return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK); /*Retorna a lista em Json*/
    }
    
    @PostMapping(value = "salvar")
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> salvar (@RequestBody Usuario usuario) { /*Recebe os dados para salvar*/
    	
    	Usuario user =  usuarioRepository.save(usuario);
    	return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
    	
    }
    
    @PutMapping(value = "atualizar")
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<?> atualizar (@RequestBody Usuario usuario) { /*Recebe os dados para salvar*/
    	
    	if (usuario.getId() == 0) {
    		return new ResponseEntity<String>("Id nao informado", HttpStatus.OK);
    	}
    	
    	Usuario user =  usuarioRepository.saveAndFlush(usuario);
    	
    	return new ResponseEntity<Usuario>(user, HttpStatus.OK);
    	
    }
    
    
    @DeleteMapping(value = "delete")
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<String> delete (@RequestParam Long iduser) { /*Recebe os dados para deletar*/
    	
    	usuarioRepository.deleteById(iduser);
    	return new ResponseEntity<String>("Usuário deletado com suceso", HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscaruserid")
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<Usuario> buscaruserid (@RequestParam(name = "iduser") Long iduser) { /*Recebe os dados para consultar*/
    	
    	Usuario usuario = usuarioRepository.findById(iduser).get();
    	return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	
    }
    
    @GetMapping(value = "buscarpornome")
    @ResponseBody /*Descrição da resposta*/
    public ResponseEntity<List<Usuario>> buscarPorNome (@RequestParam(name = "name") String name) { /*Recebe os dados para consultar*/
    	
    	List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());
    	
    	return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);
    	
    }
    
    
    
    
    
    
    
    
    
    
}
