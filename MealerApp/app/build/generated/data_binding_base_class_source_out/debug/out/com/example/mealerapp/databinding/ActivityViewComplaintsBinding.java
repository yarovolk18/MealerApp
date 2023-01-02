// Generated by view binder compiler. Do not edit!
package com.example.mealerapp.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.mealerapp.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityViewComplaintsBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final TextView complaintFive;

  @NonNull
  public final TextView complaintFour;

  @NonNull
  public final TextView complaintOne;

  @NonNull
  public final TextView complaintThree;

  @NonNull
  public final TextView complaintTwo;

  @NonNull
  public final EditText cookId;

  @NonNull
  public final Button goLeft;

  @NonNull
  public final Button goRight;

  @NonNull
  public final Switch isPermanent;

  @NonNull
  public final TextView listComplaints;

  @NonNull
  public final Button suspendAction;

  @NonNull
  public final EditText suspensionDate;

  private ActivityViewComplaintsBinding(@NonNull ConstraintLayout rootView,
      @NonNull TextView complaintFive, @NonNull TextView complaintFour,
      @NonNull TextView complaintOne, @NonNull TextView complaintThree,
      @NonNull TextView complaintTwo, @NonNull EditText cookId, @NonNull Button goLeft,
      @NonNull Button goRight, @NonNull Switch isPermanent, @NonNull TextView listComplaints,
      @NonNull Button suspendAction, @NonNull EditText suspensionDate) {
    this.rootView = rootView;
    this.complaintFive = complaintFive;
    this.complaintFour = complaintFour;
    this.complaintOne = complaintOne;
    this.complaintThree = complaintThree;
    this.complaintTwo = complaintTwo;
    this.cookId = cookId;
    this.goLeft = goLeft;
    this.goRight = goRight;
    this.isPermanent = isPermanent;
    this.listComplaints = listComplaints;
    this.suspendAction = suspendAction;
    this.suspensionDate = suspensionDate;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityViewComplaintsBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityViewComplaintsBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_view_complaints, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityViewComplaintsBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.complaint_five;
      TextView complaintFive = ViewBindings.findChildViewById(rootView, id);
      if (complaintFive == null) {
        break missingId;
      }

      id = R.id.complaint_four;
      TextView complaintFour = ViewBindings.findChildViewById(rootView, id);
      if (complaintFour == null) {
        break missingId;
      }

      id = R.id.complaint_one;
      TextView complaintOne = ViewBindings.findChildViewById(rootView, id);
      if (complaintOne == null) {
        break missingId;
      }

      id = R.id.complaint_three;
      TextView complaintThree = ViewBindings.findChildViewById(rootView, id);
      if (complaintThree == null) {
        break missingId;
      }

      id = R.id.complaint_two;
      TextView complaintTwo = ViewBindings.findChildViewById(rootView, id);
      if (complaintTwo == null) {
        break missingId;
      }

      id = R.id.cook_id;
      EditText cookId = ViewBindings.findChildViewById(rootView, id);
      if (cookId == null) {
        break missingId;
      }

      id = R.id.go_left;
      Button goLeft = ViewBindings.findChildViewById(rootView, id);
      if (goLeft == null) {
        break missingId;
      }

      id = R.id.go_right;
      Button goRight = ViewBindings.findChildViewById(rootView, id);
      if (goRight == null) {
        break missingId;
      }

      id = R.id.is_permanent;
      Switch isPermanent = ViewBindings.findChildViewById(rootView, id);
      if (isPermanent == null) {
        break missingId;
      }

      id = R.id.listComplaints;
      TextView listComplaints = ViewBindings.findChildViewById(rootView, id);
      if (listComplaints == null) {
        break missingId;
      }

      id = R.id.suspend_action;
      Button suspendAction = ViewBindings.findChildViewById(rootView, id);
      if (suspendAction == null) {
        break missingId;
      }

      id = R.id.suspension_date;
      EditText suspensionDate = ViewBindings.findChildViewById(rootView, id);
      if (suspensionDate == null) {
        break missingId;
      }

      return new ActivityViewComplaintsBinding((ConstraintLayout) rootView, complaintFive,
          complaintFour, complaintOne, complaintThree, complaintTwo, cookId, goLeft, goRight,
          isPermanent, listComplaints, suspendAction, suspensionDate);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}