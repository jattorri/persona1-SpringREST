package com.example.api.repositories;

import com.example.api.entities.Persona;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends BaseRepository <Persona, Long> {

    List<Persona> findByNombreContainingOrApellidoContaining(String nombre, String Apellido);
    Page<Persona> findByNombreContainingOrApellidoContaining(String nombre, String Apellido, Pageable pageable);

    @Query(value="SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    List<Persona> search (@Param ("filtro")String filtro);
    @Query(value="SELECT p FROM Persona p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro%")
    Page<Persona> search (@Param ("filtro")String filtro, Pageable pageable);

    @Query(
            value= "SELECT * FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            nativeQuery = true
    )
    List<Persona>searchNativo(@Param("filtro")String filtro);
    @Query(
            value= "SELECT * FROM persona WHERE persona.nombre LIKE %:filtro% OR persona.apellido LIKE %:filtro%",
            nativeQuery = true,
            countQuery="SELECT count(*) FROM persona"
    )
    Page<Persona>searchNativo(@Param("filtro")String filtro, Pageable pageable);



}
