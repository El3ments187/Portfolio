package com.inprogress.portfolio.commands;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by David Munsell on 7/31/18.
 */
@Getter
@Setter
@NoArgsConstructor
public class StockCommand {
    private Long id;

    @NotBlank
    @Size(min = 1, max = 10)
    private String stockSymbol;

    private Integer numberOfShares;

    private BigDecimal stockPurchasePrice;

    private BigDecimal stockCurrentPrice;

}
