package com.coffee.coffee.logic.controller;

import com.coffee.coffee.logic.NotEnoughGrainException;
import com.coffee.coffee.logic.NotEnoughWaterException;
import com.coffee.coffee.logic.NotEnouhgMilkException;
import com.coffee.coffee.logic.bd.BdEntity;
import com.coffee.coffee.logic.bd.BdService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Контроллер кофемашины")
public class CoffeeMachine {
    private int grainCapacity = 100;
    private int waterCapacity = 100;
    private int milkCapacity  = 100;

    @Autowired
    public BdService bdService;

    public void save(String brewed){
        BdEntity bdEntity = new BdEntity();

        bdEntity.setWhatBrewed(brewed);
        bdEntity.setLeftGrain(grainCapacity);
        bdEntity.setLeftMilk(milkCapacity);
        bdEntity.setLeftWater(waterCapacity);
        bdService.save(bdEntity);
    }

    public CoffeeMachine(){}

    @GetMapping(value = "/fillMilk")
    @Operation(description = "Наполнить емкость с молоком")
    public void fillMilkCapacity(){
        milkCapacity = 100;
    }

    @GetMapping(value = "/fillGrain")
    @Operation(description = "Наполнить емкость с зернами")
    public void fillGrainCapacity(){
        grainCapacity = 100;
    }

    @GetMapping(value = "/fillWater")
    @Operation(description = "Наполнить емкость с водой")
    public void fillWaterCapacity(){
        waterCapacity = 100;
    }

    public int getGrainCapacity() {
        return grainCapacity;
    }
    public int getWaterCapacity() {
        return waterCapacity;
    }
    public int getMilkCapacity() {
        return milkCapacity;
    }

    public void cookBlackCoffee(){
        if(grainCapacity < 6){
            throw new NotEnoughGrainException();
        } else if(waterCapacity < 6){
            throw new NotEnoughWaterException();
        }

        waterCapacity -= 6;
        grainCapacity -= 6;
        save("black");
    }
    public void cookEspressoCoffee() {
        if(grainCapacity < 6){
            throw new NotEnoughGrainException();
        } else if(waterCapacity < 6){
            throw new NotEnoughWaterException();
        }

        waterCapacity -= 6;
        grainCapacity -= 6;
        save("espresso");
    }
    public void cookLatteCoffee(){
        if(grainCapacity < 4){
            throw new NotEnoughGrainException();
        } else if(waterCapacity < 4){
            throw new NotEnoughWaterException();
        } else if(milkCapacity < 4){
            throw new NotEnouhgMilkException();
        }

        waterCapacity -= 4;
        grainCapacity -= 4;
        milkCapacity -= 4;
        save("latte");
    }
    public void cookCappuccinoCoffee(){
        if(grainCapacity < 3){
            throw new NotEnoughGrainException();
        } else if(waterCapacity < 3){
            throw new NotEnoughWaterException();
        } else if(milkCapacity < 6){
            throw new NotEnouhgMilkException();
        }

        waterCapacity -= 3;
        grainCapacity -= 3;
        milkCapacity -= 6;
        save("cappuccino");
    }

    @RequestMapping(name = "Black",value = "/makeCoffee", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Сделать кофе")
    public int makeCoffee(String name){
        try {
            switch (name){
                case "Black": {
                    cookBlackCoffee();
                    return 1;
                }
                case "Espresso": {
                    cookEspressoCoffee();
                    return 1;
                }
                case "Latte": {
                    cookLatteCoffee();
                    return 1;
                }
                case "Cappuccino": {
                    cookCappuccinoCoffee();
                    return 1;
                }
                default: return 0;
            }
        } catch (NotEnoughGrainException e) {
            return -1;
        } catch (NotEnoughWaterException e){
            return -2;
        } catch (NotEnouhgMilkException e){
            return -3;
        }
    }

    @RequestMapping(value = "/getCapacity", method = RequestMethod.GET,
    produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(description = "Получить оставшееся количество в емкостях массивом")
    public int[] getCapacity(){
        int[] result = new int[3];
        result[0] = getWaterCapacity();
        result[1] = getMilkCapacity();
        result[2] = getGrainCapacity();

        return result;
    }
}
