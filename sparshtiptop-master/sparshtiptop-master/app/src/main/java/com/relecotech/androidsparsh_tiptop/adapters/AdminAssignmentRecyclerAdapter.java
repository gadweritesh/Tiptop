package com.relecotech.androidsparsh_tiptop.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.relecotech.androidsparsh_tiptop.R;
import com.relecotech.androidsparsh_tiptop.models.AssignmentListData;
import com.relecotech.androidsparsh_tiptop.utils.SessionManager;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import static com.relecotech.androidsparsh_tiptop.MainActivity.sharedPrefValue;

/**
 * Created by Amey on 26-05-2018.
 */

public class AdminAssignmentRecyclerAdapter extends BaseAdapter {
    private final SessionManager sessionManager;
    private final HashMap<String, String> userDetails;
    protected List<AssignmentListData> listAssListDatas;
    Context context;
    LayoutInflater inflater;
    String scoreType;
    private String userRole;
    private int count;
    private SimpleDateFormat dateFormat;


    public AdminAssignmentRecyclerAdapter(Context context, List<AssignmentListData> listAssListDatas) {
        this.listAssListDatas = listAssListDatas;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        dateFormat = new SimpleDateFormat("d MMM yyyy", Locale.getDefault());
        sessionManager = new SessionManager(context, sharedPrefValue);
        userDetails = sessionManager.getUserDetails();
    }

    public int getCount() {
        return listAssListDatas.size();
    }

    public AssignmentListData getItem(int position) {
        return listAssListDatas.get(position);
    }

    public long getItemId(int position) {
        return 0;
    }



    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {

            holder = new ViewHolder();
            convertView = this.inflater.inflate(R.layout.admin_assignment_list_item, parent, false);

            holder.assignment_title = (TextView) convertView.findViewById(R.id.assignment_title);
            holder.assignment_Body = (TextView) convertView.findViewById(R.id.assignment_Body);
            holder.assignmentPostDate = (TextView) convertView.findViewById(R.id.assignment_DateTime);

            holder.creditsStdntNumerator = (TextView) convertView.findViewById(R.id.creditsNumeratorTextView);
            holder.attachemntStatusIcon = (ImageView) convertView.findViewById(R.id.assignmentAttachmentPin);
            holder.creditsStdntDenominator = (TextView) convertView.findViewById(R.id.creditsDenominatorTextView);
            holder.teacherCreditNotation = (TextView) convertView.findViewById(R.id.teacher_credits_textView);
            holder.markText_view = (TextView) convertView.findViewById(R.id.markText_text_view);
            holder.txtSubmittedBy = (TextView) convertView.findViewById(R.id.submittedByTextView);

            holder.likeTv = (TextView) convertView.findViewById(R.id.assignmentLikeCountTextView);

            holder.teacherCreditlayout = (RelativeLayout) convertView.findViewById(R.id.teacher_credits_panel);
            holder.studentCreditlayout = (RelativeLayout) convertView.findViewById(R.id.student_credits_panel);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        AssignmentListData assListData = listAssListDatas.get(position);
        holder.assignment_title.setText(assListData.getSubject());
        holder.assignment_Body.setText(assListData.getDescription().replace("\\n", "\n").replace("\\", ""));

        holder.assignmentPostDate.setText(dateFormat.format(assListData.getIssueDate()));
        System.out.println(assListData.getLikeCount());
        holder.likeTv.setText(String.valueOf(assListData.getLikeCount()));
        holder.txtSubmittedBy.setText("Submitted By : "+assListData.getSubmittedBy());

        String scoreType = assListData.getScoreType();

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT,
                3.0f
        );
        try {
            count = Integer.parseInt(assListData.getAttachmentCount());
        } catch (Exception e) {
            System.out.println(" Exception attachment Count " + e.getMessage());
        }
        System.out.println(" assListData.getAttachmentCount() " + assListData.getAttachmentCount());
        if (count == 0) {
            holder.attachemntStatusIcon.setVisibility(View.INVISIBLE);
        } else {
            holder.attachemntStatusIcon.setVisibility(View.VISIBLE);
        }

        userRole = userDetails.get(SessionManager.KEY_USER_ROLE);
//        if (userRole.equals("Teacher")) {
            holder.teacherCreditlayout.setVisibility(View.VISIBLE);
            holder.studentCreditlayout.setVisibility(View.INVISIBLE);


            if (scoreType.equals("Grades")) {

                holder.teacherCreditNotation.setText("Grade");
                holder.teacherCreditNotation.setTextSize(12);
                holder.markText_view.setText("");

            } else if (scoreType.equals("Marks")) {

                holder.teacherCreditNotation.setText(assListData.getMaxCredits());
                holder.markText_view.setText("Marks");
                holder.markText_view.setTextSize(8);
            }

        return convertView;
    }


    private class ViewHolder {
        TextView assignment_title;
        TextView assignment_Body;
        TextView assignmentPostDate;
        TextView txtDuedate;
        TextView txtissuedate;
        TextView creditsStdntNumerator;
        TextView creditsStdntDenominator;
        TextView teacherCreditNotation;
        TextView markText_view;
        TextView likeTv;

        ImageView attachemntStatusIcon;
        TextView txtSubmittedBy;
        RelativeLayout teacherCreditlayout;
        RelativeLayout studentCreditlayout;

    }
}
