package com.hospitalVM.atenciones.repositories;

import com.hospitalVM.atenciones.models.Atencion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AtencionRepository extends JpaRepository<Atencion, Long> {

    Optional<Atencion> findById(Long id);
}
