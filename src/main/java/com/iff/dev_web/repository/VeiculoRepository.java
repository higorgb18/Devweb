package com.iff.dev_web.repository;

import com.iff.dev_web.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.marca = :marca")
    List<Veiculo> buscarVeiculosPorMarca(@Param("marca") String marca);

    @Query("SELECT v FROM Veiculo v WHERE v.modelo = :modelo")
    List<Veiculo> buscarVeiculosPorModelo(@Param("modelo") String modelo);

    @Query("SELECT v FROM Veiculo v WHERE v.anoModelo = :anoFabricao")
    List<Veiculo> buscarVeiculosPorAno(@Param("anoFabricao") Integer anoFabricao);

    @Query("SELECT v FROM Veiculo v WHERE v.valor = :valorMax")
    List<Veiculo> buscarVeiculosPorValor(@Param("valorMax") BigDecimal valorMax);

    Optional<Veiculo> findByPlaca(String placa);

    Optional<Veiculo> findByChassi(String chassi);
}
