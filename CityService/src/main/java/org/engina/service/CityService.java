package org.engina.service;

import org.engina.dto.request.CityRequestDto;
import org.engina.exception.CityServiceException;
import org.engina.exception.ErrorType;
import org.engina.mapper.ICityMapper;
import org.engina.rabbitmq.model.SaveGuessModel;
import org.engina.rabbitmq.producer.GuessProducer;
import org.engina.repository.ICityRepository;
import org.engina.repository.entity.City;
import org.engina.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService extends ServiceManager<City,Long> {
    private final ICityRepository repository;
    private final GuessProducer guessProducer;

    public CityService(ICityRepository repository, GuessProducer guessProducer) {
        super(repository);
        this.repository = repository;
        this.guessProducer = guessProducer;
    }
    public String saveCity(CityRequestDto dto) { //bu metod alınan şehir kaydını rabbitmq ile aynı zamanda tblguess' ekliyor.
        City city = ICityMapper.INSTANCE.toCity(dto);
        save(city);
        guessProducer.convertAndSend(SaveGuessModel.builder()
                .cityid(city.getId())
                .cityName(city.getCityName())
                .cityArea(city.getCityArea())
                .hint(city.getHint())
                .photoWithBlur(city.getPhotoWithBlur())
                .photoWithoutBlur(city.getPhotoWithoutBlur())
                .build());
        return "Kayıt başarılı!";
    }

    public Optional<City> getCity(Long cityId) {
        if (repository.findById(cityId).isEmpty()) {
            throw new CityServiceException(ErrorType.CITY_NOT_FOUND);
        }
        return repository.findById(cityId);
    }
}
