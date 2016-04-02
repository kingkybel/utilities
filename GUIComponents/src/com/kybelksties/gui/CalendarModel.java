package com.kybelksties.gui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author dieter
 */
public class CalendarModel extends AbstractTableModel {

    private ArrayList<String> weekDaysLong;
    private ArrayList<String> weekDaysShort;
    private ArrayList<String> monthsLong;
    private ArrayList<String> monthsShort;
    private int dayOffset = 0;
    private int daysInMonth = 31;
    private int daysInPreviousMonth = 31;
    private int rows = 5;
    private int[][] daysTableData;

    final void initializeNameLists(Locale locale) {
        weekDaysLong = makeLocalNameArray(Calendar.DAY_OF_WEEK, Calendar.LONG, locale);
        weekDaysShort = makeLocalNameArray(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale);
        monthsLong = makeLocalNameArray(Calendar.MONTH, Calendar.LONG, locale);
        monthsShort = makeLocalNameArray(Calendar.MONTH, Calendar.SHORT, locale);
    }

    private static ArrayList<String> makeLocalNameArray(int type,
            int style,
            Locale locale) {
        ArrayList<String> reval = new ArrayList<>();
        Map<Integer, String> sorted = new TreeMap<>();
        Map<String, Integer> calendarMap
                = Calendar.getInstance().getDisplayNames(type, style, locale);
        for (String s : calendarMap.keySet()) {
            sorted.put(calendarMap.get(s), s);
        }
        for (Integer i : sorted.keySet()) {
            reval.add(sorted.get(i));
        }
        return reval;
    }

    public CalendarModel() {
        this(Locale.getDefault());
    }

    public CalendarModel(Locale locale) {
        this(locale, Calendar.getInstance().getTime());
    }

    public CalendarModel(Locale locale, Date date) {
        initializeNameLists(locale);
        final Calendar calendar = Calendar.getInstance(locale);
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        dayOffset = calendar.get(Calendar.DAY_OF_WEEK);
        int monthContainingDate = calendar.get(Calendar.MONTH);
        int previousMonth = monthContainingDate == 1
                ? 12
                : monthContainingDate - 1;
        daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        rows = (daysInMonth + dayOffset) / 7;
        calendar.set(Calendar.MONTH, previousMonth);
        daysInPreviousMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        daysTableData = new int[rows][7];
        if (dayOffset > 0) {
            for (int i = dayOffset - 1; i > 0; i--) {
                daysTableData[0][dayOffset - i] = daysInPreviousMonth - i;
            }
        }
        for (int i = dayOffset; i < rows * 7; i++) {
            daysTableData[i / 7][i % 7] = i % daysInMonth;
        }
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return 7;
    }

    @Override
    public Object getValueAt(int row, int col) {
        if(daysTableData != null)
            return daysTableData[row][col];
        return null;
    }

    @Override
    public String getColumnName(int col) {
        return weekDaysShort.get(col);
    }

    public ArrayList<String> getWeekDaysLong() {
        return weekDaysLong;
    }

    public ArrayList<String> getWeekDaysShort() {
        return weekDaysShort;
    }

    public ArrayList<String> getMonthsLong() {
        return monthsLong;
    }

    public ArrayList<String> getMonthsShort() {
        return monthsShort;
    }

}
