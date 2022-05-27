package com.edikorce.FreeJobs.controller;


import com.edikorce.FreeJobs.model.Item;
import com.edikorce.FreeJobs.service.ItemService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/item")
@CrossOrigin
public class ItemController {


    @Autowired
    private ItemService itemService;




    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/register")
    public ResponseEntity<Item> saveItem(@RequestParam(value = "item") String item, @RequestParam(value = "imgFile") MultipartFile imgFile) throws Exception {

        Item item1 = new ObjectMapper().readValue(item, Item.class);

        return new ResponseEntity<>(itemService.saveItem(item1, imgFile), HttpStatus.OK);

    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/user")
    public List<Item> getItemsByUserId(@RequestParam("id") Long id){

        return itemService.getAllItemsByUserId(id);
    }

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @RequestMapping("/all")
    public List<Item> getAllItems(){

        return itemService.getAllItems();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItemById(@RequestParam(value = "id") Long id) throws Exception {


        itemService.deleteItemById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
