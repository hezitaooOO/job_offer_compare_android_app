package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import java.util.ArrayList;
import java.util.List;

public final class DatabaseJob {
    private DatabaseJob() {}

    public static final String TABLE_NAME = "job";
    public static final String COLUMN_NAME_IS_CURRENT = "is_current";
    public static final String COLUMN_NAME_TITLE = "title";
    public static final String COLUMN_NAME_COMPANY = "company";
    public static final String COLUMN_NAME_CITY = "city";
    public static final String COLUMN_NAME_STATE = "state";
    public static final String COLUMN_NAME_COST_OF_LIVING_INDEX = "cost_of_living_index";
    public static final String COLUMN_NAME_YEARLY_SALARY = "yearly_salary";
    public static final String COLUMN_NAME_YEARLY_BONUS = "yearly_bonus";
    public static final String COLUMN_NAME_RETIREMENT_BENEFITS = "retirement_benefits";
    public static final String COLUMN_NAME_RELOCATION_STIPEND = "relocation_stipend";
    public static final String COLUMN_NAME_TRAINING_FUND = "training_fund";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_IS_CURRENT + " INTEGER," +
                    COLUMN_NAME_TITLE + " TEXT," +
                    COLUMN_NAME_COMPANY + " TEXT," +
                    COLUMN_NAME_CITY + " TEXT," +
                    COLUMN_NAME_STATE + " TEXT," +
                    COLUMN_NAME_COST_OF_LIVING_INDEX + " INTEGER," +
                    COLUMN_NAME_YEARLY_SALARY + " REAL," +
                    COLUMN_NAME_YEARLY_BONUS + " REAL," +
                    COLUMN_NAME_RETIREMENT_BENEFITS + " INTEGER," +
                    COLUMN_NAME_RELOCATION_STIPEND + " REAL," +
                    COLUMN_NAME_TRAINING_FUND + " REAL)";

    public static void initializeTable(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public static CurrentJob getCurrentJob(SQLiteDatabase db) {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                COLUMN_NAME_IS_CURRENT + " = ?",
                new String[]{"1"},
                null,
                null,
                null
        );
        if (cursor.getCount() == 0) {
            return null;
        }

        cursor.moveToFirst();

        CurrentJob currentJob =  new CurrentJob(
            cursor.getString(1),
            cursor.getString(2),
            new Location(
                cursor.getString(3),
                cursor.getString(4)
            ),
            cursor.getInt(5),
            cursor.getDouble(6),
            cursor.getDouble(7),
            cursor.getInt(8),
            cursor.getDouble(9),
            cursor.getDouble(10)
        );

        cursor.close();
        return currentJob;
    }

    public static List<JobOffer> getJobOffers(SQLiteDatabase db) {
        Cursor cursor = db.query(
                TABLE_NAME,
                null,
                COLUMN_NAME_IS_CURRENT + " = ?;",
                new String[]{"0"},
                null,
                null,
                null
        );

        List<JobOffer> jobOffers = new ArrayList<JobOffer>();

        while (cursor.moveToNext()) {
            jobOffers.add(
                new JobOffer(
                        cursor.getString(1),
                        cursor.getString(2),
                        new Location(
                                cursor.getString(3),
                                cursor.getString(4)
                        ),
                        cursor.getInt(5),
                        cursor.getDouble(6),
                        cursor.getDouble(7),
                        cursor.getInt(8),
                        cursor.getDouble(9),
                        cursor.getDouble(10)
                )
            );
        }

        cursor.close();
        return jobOffers;
    }

    private static void addJob(
            SQLiteDatabase db,
            String title,
            String company,
            Location location,
            int costOfLivingIndex,
            double yearlySalary,
            double yearlyBonus,
            int retirementBenefits,
            double relocationStipend,
            double trainingFund,
            boolean isCurrent
    ) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_IS_CURRENT, isCurrent ? 1:0);
        values.put(COLUMN_NAME_TITLE, title);
        values.put(COLUMN_NAME_COMPANY, company);
        values.put(COLUMN_NAME_CITY, location.getCity());
        values.put(COLUMN_NAME_STATE, location.getState());
        values.put(COLUMN_NAME_COST_OF_LIVING_INDEX, costOfLivingIndex);
        values.put(COLUMN_NAME_YEARLY_SALARY, yearlySalary);
        values.put(COLUMN_NAME_YEARLY_BONUS, yearlyBonus);
        values.put(COLUMN_NAME_RETIREMENT_BENEFITS, retirementBenefits);
        values.put(COLUMN_NAME_RELOCATION_STIPEND, relocationStipend);
        values.put(COLUMN_NAME_TRAINING_FUND, trainingFund);

        db.insert(TABLE_NAME, null, values);
    }

    public static void addJobOffer(
            SQLiteDatabase db,
            String title,
            String company,
            Location location,
            int costOfLivingIndex,
            double yearlySalary,
            double yearlyBonus,
            int retirementBenefits,
            double relocationStipend,
            double trainingFund
    ) {
        addJob(db, title, company, location, costOfLivingIndex, yearlySalary, yearlyBonus,
                retirementBenefits, relocationStipend, trainingFund, false);
    }

    public static void addEditCurrentJob(
            SQLiteDatabase db,
            String title,
            String company,
            Location location,
            int costOfLivingIndex,
            double yearlySalary,
            double yearlyBonus,
            int retirementBenefits,
            double relocationStipend,
            double trainingFund
    ) {
        // delete column with is_current = true (if any)
        db.delete(
                TABLE_NAME,
                COLUMN_NAME_IS_CURRENT + " = ?;",
                new String[]{"1"}
        );

        // insert a new column with is_current
        addJob(db, title, company, location, costOfLivingIndex, yearlySalary, yearlyBonus,
                retirementBenefits, relocationStipend, trainingFund, true);
    }
}


