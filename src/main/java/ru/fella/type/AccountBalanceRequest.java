package ru.fella.type;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@Data
@XmlRootElement
public class AccountBalanceRequest {
    private String clientName;
    private String clientSurname;
    private Long accountNumber;
    private BigDecimal accountBalance;

}
