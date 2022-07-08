package com.example.cubwork.repository;
import com.example.cubwork.entity.CoinSetting;
import lombok.var;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * @author light
 */
public interface CoindeskRepository extends JpaRepository<CoinSetting, String> {

   List<CoinSetting> findCoinSettingsByCodeContains(String code);
}
