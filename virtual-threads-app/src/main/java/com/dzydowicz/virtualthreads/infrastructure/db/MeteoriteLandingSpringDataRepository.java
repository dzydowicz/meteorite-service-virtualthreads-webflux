package com.dzydowicz.virtualthreads.infrastructure.db;

import com.dzydowicz.virtualthreads.domain.model.MeteoriteLanding;
import com.dzydowicz.virtualthreads.domain.model.MeteoriteLandingFilter;
import com.dzydowicz.virtualthreads.domain.repository.MeteoriteLandingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
class MeteoriteLandingSpringDataRepository implements MeteoriteLandingRepository {

    private final MeteoriteLandingCrudRepository meteoriteLandingCrudRepository;

    @Override
    public MeteoriteLanding save(MeteoriteLanding meteoriteLanding) {
        MeteoriteLanding result = null;
        if (meteoriteLanding != null) {
            MeteoriteLandingEntity entity = MeteoriteLandingEntityConverter.toEntity(meteoriteLanding);
            MeteoriteLandingEntity savedEntity = meteoriteLandingCrudRepository.save(entity);

            result = MeteoriteLandingEntityConverter.toDomain(savedEntity);
        }

        return result;
    }

    @Override
    public MeteoriteLanding getMeteoriteLanding(Long id) {
        return MeteoriteLandingEntityConverter.toDomain(meteoriteLandingCrudRepository.findMeteoriteLandingById(id));
    }

    @Override
    public List<MeteoriteLanding> getMeteoriteLandings(MeteoriteLandingFilter meteoriteLandingFilter) {
        return meteoriteLandingCrudRepository.findMeteoriteLandings(meteoriteLandingFilter).stream()
                .map(MeteoriteLandingEntityConverter::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        meteoriteLandingCrudRepository.deleteById(id);
    }
}
