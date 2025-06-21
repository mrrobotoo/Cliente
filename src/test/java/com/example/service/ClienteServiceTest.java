package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.model.Cliente;
import com.example.model.TipoCliente;
import com.example.repository.ClienteRepository;

public class ClienteServiceTest  {
	@Mock
	private ClienteRepository clienteRepository;
	
	@InjectMocks
	private ClienteService clienteService;
	
	private Cliente clienteVIP;
	private Cliente clienteRegular;
	
	@BeforeEach
	void setUp() {
	    MockitoAnnotations.openMocks(this);
	    clienteVIP = new Cliente("1", "Juan Pérez", "juan@example.com", 30, TipoCliente.VIP);
	    clienteRegular = new Cliente("2", "María Gómez", "maria@example.com", 25, TipoCliente.REGULAR);
	}
	
	@Test
	void testCreateCliente() {
	    when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteVIP);
	
	    var result = clienteService.create(clienteVIP);
	
	    assertNotNull(result);
	    assertEquals("1", result.id());
	    assertEquals("Juan Pérez", result.nombre());
	    assertEquals("juan@example.com", result.email());
	    assertEquals(30, result.edad());
	    assertEquals(TipoCliente.VIP, result.tipoCliente());
	    verify(clienteRepository, times(1)).save(clienteVIP);
	}
	
	@Test
	void testGetById_Success() {
	    when(clienteRepository.findById("1")).thenReturn(Optional.of(clienteVIP));
	
	    var result = clienteService.getById("1");
	
	    assertNotNull(result);
	    assertEquals("1", result.id());
	    assertEquals("Juan Pérez", result.nombre());
	    verify(clienteRepository, times(1)).findById("1");
	}
	
	@Test
	void testGetById_NotFound() {
	    when(clienteRepository.findById("999")).thenReturn(Optional.empty());
	
	    var exception = assertThrows(RuntimeException.class, () -> clienteService.getById("999"));
	    assertEquals("Entity not found with id 999", exception.getMessage());
	    verify(clienteRepository, times(1)).findById("999");
	}
	
	@Test
	void testGetAll() {
	    when(clienteRepository.findAll()).thenReturn(List.of(clienteVIP, clienteRegular));
	
	    var result = clienteService.getAll();
	
	    assertEquals(2, result.size());
	    assertTrue(result.contains(clienteVIP));
	    assertTrue(result.contains(clienteRegular));
	    verify(clienteRepository, times(1)).findAll();
	}
	
	@Test
	void testGetAll_EmptyList() {
	    when(clienteRepository.findAll()).thenReturn(List.of());
	
	    var result = clienteService.getAll();
	
	    assertTrue(result.isEmpty());
	    verify(clienteRepository, times(1)).findAll();
	}
	
	@Test
	void testUpdateCliente_Success() {
	    var updatedCliente = new Cliente("1", "Juan Updated", "juan.updated@example.com", 31, TipoCliente.REGULAR);
	    when(clienteRepository.findById("1")).thenReturn(Optional.of(clienteVIP));
	
	    
	    clienteService.update(updatedCliente, "1");
	
	    
	    verify(clienteRepository, times(1)).update(updatedCliente, "1");
	}
	
	@Test
	void testUpdateCliente_NotFound() {
	  
	    when(clienteRepository.findById("999")).thenReturn(Optional.empty());
	
	    
	    var exception = assertThrows(RuntimeException.class, () -> clienteService.getById("999"));
	    assertEquals("Entity not found with id 999", exception.getMessage());
	    verify(clienteRepository, times(1)).findById("999");
	}
	
	@Test
	void testDeleteCliente() {

	    clienteService.delete("1");
	
	    verify(clienteRepository, times(1)).deleteById("1");
	}
	
	@Test
	void testCalcularDescuento_VIP() {

	    when(clienteRepository.findById("1")).thenReturn(Optional.of(clienteVIP));
	
	
	    var descuento = clienteService.calcularDescuento("1");
	
	    assertEquals(0.20, descuento, 0.01);
	    verify(clienteRepository, times(1)).findById("1");
	}
	
	@Test
	void testCalcularDescuento_Regular() {
	   
	    when(clienteRepository.findById("2")).thenReturn(Optional.of(clienteRegular));
	
	
	    var descuento = clienteService.calcularDescuento("2");
	
	 
	    assertEquals(0.0, descuento, 0.01); 
	    verify(clienteRepository, times(1)).findById("2");
	}
	
	@Test
	void testCalcularDescuento_NotFound() {
	   
	    when(clienteRepository.findById("999")).thenReturn(Optional.empty());
	
	  
	    var exception = assertThrows(RuntimeException.class, () -> clienteService.calcularDescuento("999"));
	    assertEquals("Entity not found with id 999", exception.getMessage());
	    verify(clienteRepository, times(1)).findById("999");
	}
}