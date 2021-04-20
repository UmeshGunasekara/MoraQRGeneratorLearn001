/*
 * Created by IntelliJ IDEA.
 * @Author: SLMORA
 * @DateTime: 10/25/2020 10:10 PM
 */
package com.slmora.learn.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Date;

/**
 * This Class created for
 *
 * @Author: SLMORA
 * @DateTime: 10/25/2020 10:10 PM
 * <p>
 * Version      Date            Editor              Note
 * ----------------------------------------------------------------------------------------------------------------
 * 1.0          10/25/2020      SLMORA                Initial Code
 */
@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@ToString
public class Person01
{
    final static Logger LOGGER = LogManager.getLogger(Person01.class);

    private Integer person01Id;
    private String person01FirstName;
    private String person01LastName;
    private String person01FullName;
    private Integer person01Age;
    private String person01Email;
    private Double person01Salary;
    private Date person01Birthday;
}
