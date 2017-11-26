package com.github.habiteria.core.domain.service.scheduler;

import com.github.habiteria.core.entities.CalendarRecord;
import com.github.habiteria.core.entities.Habit;
import com.github.habiteria.core.entities.Schedule;
import com.github.habiteria.core.repository.HabitRepository;
import com.github.habiteria.utils.LocalDateRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Alex Ivchenko
 */
@Slf4j
@Component("dailyGenerator")
public class DailyGeneratorImpl extends AbstractGenerator {
    public DailyGeneratorImpl(HabitRepository habitRepository) {
        super(habitRepository);
    }

    @Override
    protected boolean isSupported(Schedule schedule) {
        return schedule.getType() == Schedule.Type.DAILY;
    }

    @Override
    public Set<CalendarRecord> getAllBetween(Habit habit, LocalDate from, LocalDate to) {
        log.info("getting all between {} and {}", from, to);
        Set<CalendarRecord> generated = new HashSet<>();
        LocalDate habitStarts = habit.getSchedule().getStart().toLocalDate();
        if (habitStarts.isAfter(from)) {
            from = habitStarts;
        }
        int repeat = 1 + habitStarts.until(from).getDays();
        DayGenerator generator = new DayGenerator();
        for (LocalDate date : new LocalDateRange(from, to)) {
            CalendarRecord record = generator.generate(habit, date, repeat++);
            generated.add(record);
        }
        log.info("generated {} records", generated.size());
        return generated;
    }
}