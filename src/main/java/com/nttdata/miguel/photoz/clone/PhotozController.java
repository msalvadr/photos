package com.nttdata.miguel.photoz.clone;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.io.IOException;
import java.util.*;

@RestController
public class PhotozController {

    private final PhotozService photozService;

    public PhotozController(PhotozService photozService)  {
        this.photozService = photozService;
    }


    @GetMapping("/")
    public String hello (){
        return "Hello World";
    }

    @GetMapping("photoz")
    public Collection<Photo> get() {
        return photozService.get();
    }

    @GetMapping("photoz/{id}")
    public Photo get(@PathVariable String id) {
        Photo photo = photozService.get(id);

        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return photo;
    }

    @DeleteMapping("photoz/{id}")
    public void delete(@PathVariable String id){
        Photo photo = photozService.remove(id);
        if (photo == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/photoz")
    public Photo create(@RequestPart("data") MultipartFile file) throws IOException {

        Photo photo = photozService.save(file.getOriginalFilename(), file.getBytes());
        return photo;

    }

}

