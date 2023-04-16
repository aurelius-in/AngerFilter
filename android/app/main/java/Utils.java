public class Utils {

    /**
     * Method to get the device screen size in pixels
     * @param context the application context
     * @return Point object containing screen width and height in pixels
     */
    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    /**
     * Method to check if the device has an internet connection
     * @param context the application context
     * @return true if device is connected to internet, false otherwise
     */
    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    /**
     * Method to convert a dp value to pixels
     * @param context the application context
     * @param dp the dp value to be converted
     * @return the pixel value
     */
    public static int dpToPx(Context context, int dp) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, resources.getDisplayMetrics());
    }

    /**
     * Method to convert a pixel value to dp
     * @param context the application context
     * @param px the pixel value to be converted
     * @return the dp value
     */
    public static int pxToDp(Context context, int px) {
        Resources resources = context.getResources();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, resources.getDisplayMetrics());
    }
}
