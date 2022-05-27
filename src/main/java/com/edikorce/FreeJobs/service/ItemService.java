package com.edikorce.FreeJobs.service;

import com.edikorce.FreeJobs.model.Item;
import com.edikorce.FreeJobs.repository.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {

    @Autowired
    private ItemRepo itemRepo;

    public Item saveItem(Item item, MultipartFile imgFile) throws IOException {
       if (imgFile != null && !imgFile.isEmpty()){

           item.setItemImage(imgFile.getBytes());
       }



        return itemRepo.save(item);
    }

    public List<Item> getAllItemsByUserId(Long id){

        return itemRepo.getAllItemsByUserId(id);
    }
    public List<Item> getAllItems(){

        return itemRepo.findAll();
    }


    public void deleteItemById(Long id) throws Exception {

        Optional<Item> itemOptional = itemRepo.findById(id);
        if (itemOptional.isPresent()){
            Item item = itemOptional.get();


            itemRepo.delete(item);

        }else{
            throw new Exception("Item You are trying to delete doesn't exist");
        }


    }


}
