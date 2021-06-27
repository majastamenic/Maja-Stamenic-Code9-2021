package com.code9.tenniscourtmicroservice.validation;

import com.code9.tenniscourtmicroservice.exception.BadRequestException;
import com.code9.tenniscourtmicroservice.timeslot.domain.Timeslot;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TimeslotValidation {

    public static void checkTimeslots(Set<Timeslot> timeslots) {
        if (timeslots.isEmpty())
            throw new BadRequestException("Timeslot can not be null.");

        timeslots.stream().forEach(timeslot -> checkAreDatesNull(timeslot.getStartTime(), timeslot.getEndTime()));

        timeslots.stream().forEach(timeslot -> checkIfTermInPast(timeslot.getStartTime(), timeslot.getEndTime()));

        timeslots.stream().forEach(timeslot -> checkWorkingHours(timeslot.getStartTime(), timeslot.getEndTime()));

        timeslots.stream().forEach(timeslot -> checkDurationOfTerm(timeslot.getStartTime(), timeslot.getEndTime()));

    }

    private static void checkIfTermInPast(Date startDate, Date endDate){
        Date nowDate = new Date();
        if (startDate.before(nowDate) || endDate.before(nowDate) || endDate.before(startDate))
            throw new BadRequestException("Start date and end date can not be in the past.");
    }

    private static void checkAreDatesNull(Date startDate, Date endDate) {
        if (startDate == null || endDate == null)
            throw new BadRequestException("Start time or end time can not be null.");
    }

    private static void checkWorkingHours(Date startDate, Date endDate) {

        Calendar startCalendar = Calendar.getInstance();
        startCalendar.setTime(startDate);
        Calendar endCalendar = Calendar.getInstance();
        endCalendar.setTime(endDate);

        checkCalendarYears(startCalendar, endCalendar);
        checkCalendarMounths(startCalendar, endCalendar);
        checkCalendarDays(startCalendar, endCalendar);

        if(startCalendar.get(Calendar.DAY_OF_WEEK) != 1 && startCalendar.get(Calendar.DAY_OF_WEEK) != 7)
            checkWorkDayWorkingHours(startCalendar.get(Calendar.HOUR_OF_DAY), endCalendar.get(Calendar.HOUR_OF_DAY));
        else
            checkWeekendWorkingHours(startCalendar.get(Calendar.HOUR_OF_DAY), endCalendar.get(Calendar.HOUR_OF_DAY));

    }

    private static void checkWorkDayWorkingHours(int startHour, int endHour) {
        if (startHour < 18 || endHour > 23)
            throw new BadRequestException("Working hours on weekdays are from 18 to 23");
    }

    private static void checkWeekendWorkingHours(int startHour, int endHour) {
        if (startHour < 17 || endHour > 22)
            throw new BadRequestException("Working hours on weekend are from 17 to 22");
    }

    private static void checkCalendarYears(Calendar startCalendar, Calendar endCalendar){
        if(startCalendar.get(Calendar.YEAR) != endCalendar.get(Calendar.YEAR))
            throw new BadRequestException("Start date year and end date year must be the same.");
    }

    private static void checkCalendarMounths(Calendar startCalendar, Calendar endCalendar){
        if(startCalendar.get(Calendar.MONTH) != endCalendar.get(Calendar.MONTH))
            throw new BadRequestException("Start date mounth and end date month must be the same.");
    }

    private static void checkCalendarDays(Calendar startCalendar, Calendar endCalendar){
        if(startCalendar.get(Calendar.DAY_OF_WEEK) != endCalendar.get(Calendar.DAY_OF_WEEK))
            throw new BadRequestException("Start date day and end date day must be the same.");
    }

    private static void checkDurationOfTerm(Date startDate, Date endDate) {
        long timeDif = endDate.getTime() - startDate.getTime();
        long minutesDif = TimeUnit.MILLISECONDS.toMinutes(timeDif) % 60;
        long hoursDif = TimeUnit.MILLISECONDS.toHours(timeDif) % 24;

        if (minutesDif < 30 || hoursDif > 2)
            throw new BadRequestException("The term should last between 30 minutes and 2 hours.");
    }

}
