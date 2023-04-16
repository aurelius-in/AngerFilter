public class MyContentProvider extends ContentProvider {
    private static final String AUTHORITY = "com.example.myapp.provider";
    private static final String PATH_USERS = "users";
    private static final int URI_MATCH_USERS = 1;
    private static final UriMatcher URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        URI_MATCHER.addURI(AUTHORITY, PATH_USERS, URI_MATCH_USERS);
    }

    private MyDatabaseHelper dbHelper;

    @Override
    public boolean onCreate() {
        dbHelper = new MyDatabaseHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        switch (URI_MATCHER.match(uri)) {
            case URI_MATCH_USERS:
                cursor = db.query(MyDatabaseHelper.TABLE_USERS, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        if (cursor != null) {
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case URI_MATCH_USERS:
                return "vnd.android.cursor.dir/vnd.com.example.myapp.users";
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long id = -1;
        switch (URI_MATCHER.match(uri)) {
            case URI_MATCH_USERS:
                id = db.insert(MyDatabaseHelper.TABLE_USERS, null, values);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        if (id != -1) {
            getContext().getContentResolver().notifyChange(uri, null);
            return ContentUris.withAppendedId(uri, id);
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (URI_MATCHER.match(uri)) {
            case URI_MATCH_USERS:
                count = db.delete(MyDatabaseHelper.TABLE_USERS, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = 0;
        switch (URI_MATCHER.match(uri)) {
            case URI_MATCH_USERS:
                count = db.update(MyDatabaseHelper.TABLE_USERS, values, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Invalid URI: " + uri);
        }
        if (count > 0) {
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return count;
    }
}
