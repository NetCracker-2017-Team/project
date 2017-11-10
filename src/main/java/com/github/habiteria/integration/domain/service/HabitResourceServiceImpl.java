package com.github.habiteria.integration.domain.service;

import com.github.habiteria.core.model.Habit;
import com.github.habiteria.core.domain.habit.tracking.HabitSnapshot;
import com.github.habiteria.core.domain.habit.tracking.HabitTrackingService;
import com.github.habiteria.integration.domain.assembler.HabitSnapshotResourceAssembler;
import com.github.habiteria.integration.domain.links.Links;
import com.github.habiteria.integration.domain.resources.HabitResource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Alex Ivchenko
 */
@Service
public class HabitResourceServiceImpl implements HabitResourceService {
    private final HabitTrackingService service;
    private final HabitSnapshotResourceAssembler habitAsm;

    public HabitResourceServiceImpl(HabitTrackingService service,
                                    HabitSnapshotResourceAssembler habitAsm) {
        this.service = service;
        this.habitAsm = habitAsm;
    }

    @Override
    public Resources<HabitResource> getHabits(UUID userId, LocalDate date) {
        Set<HabitResource> habits = service.getHabits(userId, date)
                .stream()
                .map(habitAsm::toResource)
                .collect(Collectors.toSet());
        Resources<HabitResource> resource = new Resources<>(habits);
        resource.add(Links.createHabit(userId));
        return resource;
    }

    @Override
    public HabitResource getHabit(UUID userId, UUID habitId, LocalDate date) {
        HabitSnapshot habit = service.getHabit(userId, habitId, date);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource performHabit(UUID userId, UUID habitId, LocalDate date) {
        HabitSnapshot habit = service.performHabit(userId, habitId, date);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource failHabit(UUID userId, UUID habitId, LocalDate date) {
        HabitSnapshot habit = service.failHabit(userId, habitId, date);
        return habitAsm.toResource(habit);
    }

    @Override
    public HabitResource createHabit(UUID userId, Habit habit) {
        HabitSnapshot snapshot = service.createHabit(userId, habit);
        return habitAsm.toResource(snapshot);
    }

    @Override
    public Resources<HabitResource> getUnverifiedHabits(UUID userId) {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        Set<HabitResource> resources = service.getHabits(userId, yesterday)
                .stream()
                .filter(HabitSnapshot::isUnverified)
                .map(habitAsm::toResource)
                .collect(Collectors.toSet());
        return new Resources<>(resources);
    }

    @Override
    public HabitResource undoHabit(UUID userId, UUID habitId, LocalDate date) {
        return habitAsm.toResource(service.undoHabit(userId, habitId, date));
    }
}