package com.github.andygo298.testTask.controller;

import com.github.andygo298.testTask.entity.Town;
import com.github.andygo298.testTask.repository.TownRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

@RestController
@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api")
public class TownRestController {

    TownRepository townRepository;

    public TownRestController(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    Function<Long, ResponseStatusException> notFoundId = (id) -> {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Town Available with the given Id - " + id);
    };

    Function<String, ResponseStatusException> notFoundName = (name) -> {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Town Available with the given name - " + name);
    };

    Supplier<ResponseStatusException> serverError = () -> {
        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "RunTimeException from Town Service");
    };

    @ApiOperation("Returns the town using the town name passed as part of the request.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returns the town using the name of the town."),
                    @ApiResponse(code = 404, message = "No town found for the name that's passed."),
            }
    )
    @GetMapping("/town")
    public ResponseEntity<Town> getTownByName(@RequestParam String name) {
        log.info("Received the request to search by town name - {} .", name);
        Optional<Town> townOptional = townRepository.findByTownName(name.toLowerCase().trim());
        if (townOptional.isPresent()) {
            log.info("Response is : {}", townOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(townOptional.get());
        } else {
            log.info("No town available for the given town name - {}.", name);
            throw notFoundName.apply(name);
        }
    }

    @ApiOperation("Retrieves all the towns")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "SuccessFul Retrieval of towns")
            }
    )
    @GetMapping("/towns")
    public ResponseEntity<List<Town>> getAllTowns() {
        try {
            List<Town> towns = new ArrayList<>();
            log.info("Recieved request for retrieving all towns");
            townRepository.findAll().forEach(towns::add);
            if (towns.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(towns, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation("Retrieve a town using the Town id.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Returns the town for the id."),
                    @ApiResponse(code = 404, message = "No town found for the id that's passed."),
            }
    )
    @GetMapping("/towns/{id}")
    public ResponseEntity<Town> getTutorialById(@PathVariable("id") Long id) {
        Optional<Town> townOptional = townRepository.findById(id);
        if (townOptional.isPresent()) {
            log.info("Response is {}.", townOptional.get());
            return ResponseEntity.status(HttpStatus.OK).body(townOptional.get());

        } else {
            log.info("No town available with the given Town Id - {}", id);
            throw notFoundId.apply(id);
        }
    }

    @ApiOperation("Adds a new Town.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 201, message = "Town successfully added to the H2 Memory DB.")
            }
    )
    @PostMapping("/towns")
    public ResponseEntity<Town> saveEmployee(@RequestBody Town town) {
        log.info("Received the request to add a new Town in the service {} ", town);
        Town addedTown = townRepository.save(town);
        log.info("town successfully added to the DB. New town details are {} .", town);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedTown);
    }

    @ApiOperation("Updates the town details using the town's ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Town details are successfully updated to the DB."),
                    @ApiResponse(code = 404, message = "No town found for the id that's passed."),
            }
    )
    @PutMapping("/towns/{id}")
    public ResponseEntity<Town> updateTown(@PathVariable("id") long id, @RequestBody Town town) {
        log.info("Received the request to update the town. " +
                "Town Id is {} and the updated town details are {} ", id, town);
        Optional<Town> townOptional = townRepository.findById(id);

        if (townOptional.isPresent()) {
            Town updTown = townOptional.get();
            updTown.setTownName(town.getTownName());
            updTown.setPopulation(town.getPopulation());
            updTown.setNativeLanguage(town.getNativeLanguage());
            updTown.setTownInfo(town.getTownInfo());
            townRepository.save(updTown);
            return ResponseEntity.status(HttpStatus.OK).body(updTown);
        } else {
            log.info("No town available for the given town Id - {}.", id);
            throw notFoundId.apply(id);
        }
    }

    @ApiOperation("Updates the town details using the town's name.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Town details are successfully updated to the DB."),
                    @ApiResponse(code = 404, message = "No town found for the id that's passed."),
            }
    )
    @PutMapping("/towns")
    public ResponseEntity<Town> updateTownByName(@RequestParam String name, @RequestBody Town town) {
        log.info("Received the request to update the town. " +
                "Town name is {} and the updated town details are {} ", name, town);
        Optional<Town> optionalTown = townRepository.findByTownName(name.toLowerCase().trim());
        if (optionalTown.isPresent()) {
            Town updTown = optionalTown.get();
            updTown.setTownName(town.getTownName());
            updTown.setPopulation(town.getPopulation());
            updTown.setNativeLanguage(town.getNativeLanguage());
            updTown.setTownInfo(town.getTownInfo());
            townRepository.save(updTown);
            return ResponseEntity.status(HttpStatus.OK).body(updTown);
        } else {
            log.info("No town available for the given town name - {}.", name);
            throw notFoundName.apply(name);
        }
    }

    @ApiOperation("Removes the town details using the town's ID.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "Town details are successfully deleted from the DB."),
                    @ApiResponse(code = 404, message = "No town found for the id that's passed."),
            }
    )
    @DeleteMapping("/towns/{id}")
    public ResponseEntity<String> deleteTown(@PathVariable("id") long id) {
        log.info("Received the request to delete a town and the id is {} .", id);
        Optional<Town> townOptional = townRepository.findById(id);
        if (townOptional.isPresent()) {
            townRepository.deleteById(id);
            log.info("Town successfully deleted from the DB");
            return ResponseEntity.status(HttpStatus.OK).body("Town deleted successfully.");
        } else {
            log.info("No town available for the given town Id - {}.", id);
            throw notFoundId.apply(id);
        }
    }

    @ApiOperation("Removes all towns details.")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "All towns details are successfully deleted from the DB.")
            }
    )
    @DeleteMapping("/towns")
    public ResponseEntity<String> deleteAllTowns() {
        try {
            townRepository.deleteAll();
            log.info("All towns successfully deleted from the DB");
            return ResponseEntity.status(HttpStatus.OK).body("All towns deleted successfully.");
        } catch (Exception e) {
            throw serverError.get();
        }
    }

    @GetMapping("/towns/error")
    public ResponseEntity<String> errorEndpoint() {
        throw serverError.get();
    }
}

