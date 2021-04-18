package com.tomorrow.service;

import com.tomorrow.entity.Standard;

public interface StandardService {
    public Standard findAllStandard();
    public boolean updateStandard(Standard standard);
}
