package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

import androidx.annotation.Nullable;

public class DatabaseComparisonSetting {
    private DatabaseComparisonSetting() {}

    public static final String TABLE_NAME = "COMPARISON_SETTING";
    public static final String COLUMN_NAME_YEARLY_SALARY = "yearly_salary";
    public static final String COLUMN_NAME_YEARLY_BONUS = "yearly_bonus";
    public static final String COLUMN_NAME_RETIREMENT_BENEFITS = "retirement_benefits";
    public static final String COLUMN_NAME_RELOCATION_STIPEND = "relocation_stipend";
    public static final String COLUMN_NAME_TRAINING_FUND = "training_fund";

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_NAME_YEARLY_SALARY + " INTEGER," +
                    COLUMN_NAME_YEARLY_BONUS + " INTEGER," +
                    COLUMN_NAME_RETIREMENT_BENEFITS + " INTEGER," +
                    COLUMN_NAME_RELOCATION_STIPEND + " INTEGER," +
                    COLUMN_NAME_TRAINING_FUND + " INTEGER)";

    public static final String SQL_DROP_TABLE = "DROP TABLE " + TABLE_NAME;

    public static void initializeTable(SQLiteDatabase db) {
        // create the table if not exists
        db.execSQL(SQL_CREATE_ENTRIES);

        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        int count = cursor.getCount();
        cursor.close();

        // initialize all weights to 1 if not initialized
        if (count == 0) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_NAME_YEARLY_SALARY, 1);
            values.put(COLUMN_NAME_YEARLY_BONUS, 1);
            values.put(COLUMN_NAME_RETIREMENT_BENEFITS, 1);
            values.put(COLUMN_NAME_RELOCATION_STIPEND, 1);
            values.put(COLUMN_NAME_TRAINING_FUND, 1);
            db.insert(TABLE_NAME, null, values);
        }
    }

    public static ComparisonSetting getCurrentComparisonSetting(SQLiteDatabase db) {
        String[] projection = {
                COLUMN_NAME_YEARLY_SALARY,
                COLUMN_NAME_YEARLY_BONUS,
                COLUMN_NAME_RETIREMENT_BENEFITS,
                COLUMN_NAME_RELOCATION_STIPEND,
                COLUMN_NAME_TRAINING_FUND
        };

        Cursor cursor = db.query(TABLE_NAME, projection, null, null, null, null, null);
        if (cursor.getCount() == 0) {
            return new ComparisonSetting(1, 1, 1, 1, 1);
        }
        cursor.moveToFirst();
        ComparisonSetting cs =  new ComparisonSetting(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getInt(2),
                cursor.getInt(3),
                cursor.getInt(4)
        );
        cursor.close();
        return cs;
    }

    public static void adjustComparisonSetting(SQLiteDatabase db, ComparisonSetting comparisonSetting) {
        db.execSQL(SQL_DROP_TABLE);
        db.execSQL(SQL_CREATE_ENTRIES);
        ContentValues values = new ContentValues();

        values.put(COLUMN_NAME_YEARLY_SALARY, comparisonSetting.yearlySalary);
        values.put(COLUMN_NAME_YEARLY_BONUS, comparisonSetting.yearlyBonus);
        values.put(COLUMN_NAME_RETIREMENT_BENEFITS, comparisonSetting.retirementBenefits);
        values.put(COLUMN_NAME_RELOCATION_STIPEND, comparisonSetting.relocationStipend);
        values.put(COLUMN_NAME_TRAINING_FUND, comparisonSetting.trainingFund);

        db.insert(TABLE_NAME, null, values);
    }
}
