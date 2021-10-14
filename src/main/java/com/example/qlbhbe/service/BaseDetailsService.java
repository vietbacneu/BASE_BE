package com.example.qlbhbe.service;

import java.io.Serializable;
import java.util.Optional;

public interface BaseDetailsService<DT, I extends Serializable> {

    Optional<DT> findDetailsDTOById(I id);

}
