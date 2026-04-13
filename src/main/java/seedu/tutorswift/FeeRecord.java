package seedu.tutorswift;

import java.time.YearMonth;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.time.DayOfWeek;

/**
 * Tracks the per-lesson fee, paid and unpaid months for a student.
 */
public class FeeRecord {
    private int feePerLesson;
    private final ArrayList<YearMonth> paidMonths;
    private final ArrayList<YearMonth> unpaidMonths;

    public FeeRecord() {
        this.feePerLesson = 0;
        this.paidMonths = new ArrayList<>();
        this.unpaidMonths = new ArrayList<>();
    }

    public int getFeePerLesson() {
        return feePerLesson;
    }

    public void setFeePerLesson(int feePerLesson) {
        this.feePerLesson = feePerLesson;
    }

    public ArrayList<YearMonth> getPaidMonths() {
        return paidMonths;
    }

    public ArrayList<YearMonth> getUnpaidMonths() {
        return unpaidMonths;
    }

    public boolean isPaidForMonth(YearMonth month) {
        return paidMonths.contains(month);
    }

    /**
     * Marks the given month as paid.
     * Also removes it from unpaidMonths if it was previously marked unpaid.
     */
    public void markPaid(YearMonth month) {
        if (!paidMonths.contains(month)) {
            paidMonths.add(month);
        }
        unpaidMonths.remove(month);
    }

    /**
     * Marks the given month as unpaid explicitly.
     * Also removes it from paidMonths if it was previously marked paid.
     */
    public void markUnpaid(YearMonth month) {
        paidMonths.remove(month);
        if (!unpaidMonths.contains(month)) {
            unpaidMonths.add(month);
        }
    }

    /**
     * Converts fee data to save format.
     */
    public String toSaveFormat() {
        String paidPart;
        if (paidMonths.isEmpty()) {
            paidPart = "NONE";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < paidMonths.size(); i++) {
                sb.append(paidMonths.get(i).toString());
                if (i < paidMonths.size() - 1) {
                    sb.append(",");
                }
            }
            paidPart = sb.toString();
        }

        String unpaidPart;
        if (unpaidMonths.isEmpty()) {
            unpaidPart = "NONE";
        } else {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < unpaidMonths.size(); i++) {
                sb.append(unpaidMonths.get(i).toString());
                if (i < unpaidMonths.size() - 1) {
                    sb.append(",");
                }
            }
            unpaidPart = sb.toString();
        }

        return feePerLesson + ":" + paidPart + ":" + unpaidPart;
    }

    /**
     * Parses from save format string.
     */
    public static FeeRecord fromSaveFormat(String raw) {
        String[] splitParts = raw.split(":", 3);
        int fee = Integer.parseInt(splitParts[0]);
        FeeRecord record = new FeeRecord();
        record.setFeePerLesson(fee);

        if (splitParts.length >= 2 && !splitParts[1].equals("NONE")) {
            String[] months = splitParts[1].split(",");
            for (String m : months) {
                record.markPaid(YearMonth.parse(m.trim()));
            }
        }

        if (splitParts.length >= 3 && !splitParts[2].equals("NONE")) {
            String[] months = splitParts[2].split(",");
            for (String m : months) {
                YearMonth ym = YearMonth.parse(m.trim());
                if (!record.unpaidMonths.contains(ym)) {
                    record.unpaidMonths.add(ym);
                }
            }
        }

        return record;
    }

    @Override
    public String toString() {
        String feeStr = (feePerLesson == 0) ? "Not set" : "$" + feePerLesson + "/lesson";

        if (paidMonths.isEmpty() && unpaidMonths.isEmpty()) {
            return "Fee: " + feeStr + " | No payments recorded";
        }

        ArrayList<YearMonth> allMonths = new ArrayList<>();
        for (YearMonth ym : paidMonths) {
            if (!allMonths.contains(ym)) {
                allMonths.add(ym);
            }
        }
        for (YearMonth ym : unpaidMonths) {
            if (!allMonths.contains(ym)) {
                allMonths.add(ym);
            }
        }
        allMonths.sort(null);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < allMonths.size(); i++) {
            YearMonth ym = allMonths.get(i);
            String status = paidMonths.contains(ym) ? "[PAID]" : "[UNPAID]";
            sb.append(ym.getMonth()).append(" ").append(ym.getYear()).append(": ").append(status);
            if (i < allMonths.size() - 1) {
                sb.append(", ");
            }
        }

        return "Fee: " + feeStr + " | " + sb.toString();
    }

    /**
     * Calculates the total fees for a given month by aggregating the occurrences
     * of all lessons scheduled within that month.
     *
     * @param lessons A {@code List} of {@link seedu.tutorswift.Lesson} objects assigned to the student.
     * @param month The {@code YearMonth} representing the specific month for which the fees are calculated.
     * @return The total fee amount as an {@code int}, or 0 if the fee per lesson is not set.
     */
    public int calculateMonthlyTotal(List<Lesson> lessons, YearMonth month) {
        if (this.feePerLesson <= 0) {
            return 0;
        }

        int totalLessons = 0;
        for (seedu.tutorswift.Lesson lesson : lessons) {
            totalLessons += countOccurrences(lesson.getDay(), month);
        }
        return totalLessons * this.feePerLesson;
    }
    /**
     * Counts the number of times a specific {@code DayOfWeek} occurs within the given month.
     *
     * @param day The {@code DayOfWeek} to count (e.g., TUESDAY).
     * @param month The {@code YearMonth} to search within.
     * @return The total number of instances of the specified day within the month.
     */
    private int countOccurrences(DayOfWeek day, YearMonth month) {
        LocalDate firstOfMonth = month.atDay(1);
        LocalDate lastOfMonth = month.atEndOfMonth();
        int count = 0;

        // Find the first occurrence of the day in the month
        LocalDate date = firstOfMonth.with(TemporalAdjusters.nextOrSame(day));

        // Count every instance until the end of the month
        while (!date.isAfter(lastOfMonth)) {
            count++;
            date = date.plusWeeks(1);
        }
        return count;
    }
}
