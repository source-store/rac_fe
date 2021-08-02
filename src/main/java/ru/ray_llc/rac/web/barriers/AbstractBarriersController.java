package ru.ray_llc.rac.web.barriers;

/*
 * @author Alexandr.Yakubov
 **/

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.ray_llc.rac.service.EquipmentService;

public class AbstractBarriersController {

  private final Logger log = LoggerFactory.getLogger(getClass());

  @Autowired
  private EquipmentService service;


}
