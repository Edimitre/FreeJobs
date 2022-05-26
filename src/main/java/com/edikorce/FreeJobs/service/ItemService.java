package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.model.Item;
import com.edikorce.FreeJobs.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public Item saveJobItem(Item item, MultipartFile imgFile) throws IOException {
       if (imgFile != null && !imgFile.isEmpty()){

           item.setItemImage(imgFile.getBytes());
       }
        return itemRepo.save(item);
    }

    public List<Item> getAllJobItems(){

        return itemRepo.findAll();
    }




}
