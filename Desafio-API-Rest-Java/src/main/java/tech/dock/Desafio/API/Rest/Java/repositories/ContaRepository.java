package tech.dock.Desafio.API.Rest.Java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tech.dock.Desafio.API.Rest.Java.domain.Conta;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Long>{

}
