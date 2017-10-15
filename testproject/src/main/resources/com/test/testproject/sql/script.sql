/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  ElMaNu
 * Created: Oct 15, 2017
 */

CREATE SCHEMA `example_schema` ;

CREATE TABLE `example_schema`.`Log_Values` (
  `idLog_Values` INT NOT NULL AUTO_INCREMENT,
  `message` VARCHAR(500) NULL,
  `type` INT NULL,
  PRIMARY KEY (`idLog_Values`));