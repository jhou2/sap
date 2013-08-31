package com.sap.orientation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MapSectionFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	public static final String ARG_SECTION_NUMBER = "section_number";
	private static Paint paint = new Paint();
	
	
	public MapSectionFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = new SampleView(getActivity());
		return rootView;
	}

	private static class SampleView extends View {
		private static int viewWidth = 0; // view width, changes dynamically
		private static int viewHeight = 0; // view height, changes dynamically
		private static Bitmap bmLargeImage; // bitmap large enough to be
		// scrolled
		private static Rect displayRect = null; // rect we display to
		private Rect scrollRect = null; // rect we scroll over our bitmap with
		private int scrollRectX = 0; // current left location of scroll rect
		private int scrollRectY = 0; // current top location of scroll rect
		private float scrollByX = 0; // x amount to scroll by
		private float scrollByY = 0; // y amount to scroll by
		private float startX = 0; // track x from one ACTION_MOVE to the next
		private float startY = 0; // track y from one ACTION_MOVE to the next
		GestureDetector gestureDetector;
		private int zoom = 1;

		public SampleView(Context mapSectionFragment) {
			super(mapSectionFragment);
			gestureDetector = new GestureDetector(mapSectionFragment,
					new GestureListener());
			// Load a large bitmap into an offscreen area of memory.
			bmLargeImage = BitmapFactory.decodeResource(getResources(),
					R.drawable.map);
		}

		private class GestureListener extends
		GestureDetector.SimpleOnGestureListener {

			@Override
			public boolean onDown(MotionEvent e) {
				return true;
			}

			// event when double tap occurs
			@Override
			public boolean onDoubleTap(MotionEvent e) {
				float x = e.getX();
				float y = e.getY();

				Log.d("Double Tap", "Tapped at: (" + x + "," + y + ")");
				if (zoom == 1) {
					zoom = 0;
					bmLargeImage = BitmapFactory.decodeResource(getResources(),
							R.drawable.map_tiny);
					invalidate();
				} else {
					zoom = 1;
					bmLargeImage = BitmapFactory.decodeResource(getResources(),
							R.drawable.map);
					invalidate();
				}

				return true;
			}
		}

		// Our view dimensions change depending on, among other things, screen
		// orientation. onSizeChanged() is a notification that such a change has
		// occurred. For our purposes, we can use the newly changed values to
		// set up
		// our scroll and display rectangles. This is how we handle a user
		// switch
		// between portrait and landscape modes, or any other type of 'view has
		// changed' operation.
		@Override
		protected void onSizeChanged(int w, int h, int oldw, int oldh) {

			// Cache our new dimensions; we'll need them for drawing.
			viewWidth = w;
			viewHeight = h;

			// Destination rect for our main canvas draw.
			displayRect = new Rect(0, 0, viewWidth, viewHeight);
			// Scroll rect: this will be used to 'scroll around' over the
			// bitmap in memory. Initialize as above.
			scrollRect = new Rect(0, 0, viewWidth, viewHeight);

			super.onSizeChanged(w, h, oldw, oldh);
		}

		@Override
		public boolean onTouchEvent(MotionEvent event) {

			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				// Remember our initial down event location.
				startX = event.getRawX();
				startY = event.getRawY();
				break;

			case MotionEvent.ACTION_MOVE:
				float x = event.getRawX();
				float y = event.getRawY();
				// Calculate move update. This will happen many times
				// during the course of a single movement gesture.
				scrollByX = x - startX; // move update x increment
				scrollByY = y - startY; // move update y increment
				startX = x; // reset initial values to latest
				startY = y;
				invalidate(); // force a redraw
				break;
			}
			return gestureDetector.onTouchEvent(event);// true; // done with
			// this event so consume
			// it
		}

		@Override
		protected void onDraw(Canvas canvas) {

			// Our move updates are calculated in ACTION_MOVE in the opposite
			// direction
			// from how we want to move the scroll rect. Think of this as
			// dragging to
			// the left being the same as sliding the scroll rect to the right.
			int newScrollRectX = scrollRectX - (int) scrollByX;
			int newScrollRectY = scrollRectY - (int) scrollByY;
			

			// Don't scroll off the left or right edges of the bitmap.
			if (newScrollRectX < 0)
				newScrollRectX = 0;
			else if (newScrollRectX > (bmLargeImage.getWidth() - viewWidth))
				newScrollRectX = (bmLargeImage.getWidth() - viewWidth);

			// Don't scroll off the top or bottom edges of the bitmap.
			if (newScrollRectY < 0)
				newScrollRectY = 0;
			else if (newScrollRectY > (bmLargeImage.getHeight() - viewHeight))
				newScrollRectY = (bmLargeImage.getHeight() - viewHeight);

			// We have our updated scroll rect coordinates, set them and draw.
			scrollRect.set(newScrollRectX, newScrollRectY, newScrollRectX
					+ viewWidth, newScrollRectY + viewHeight);
			
			canvas.drawBitmap(bmLargeImage, scrollRect, displayRect, paint);

			// Reset current scroll coordinates to reflect the latest updates,
			// so we can repeat this update process.
			scrollRectX = newScrollRectX;
			scrollRectY = newScrollRectY;
		}
	}

} // end MapSectionFragment