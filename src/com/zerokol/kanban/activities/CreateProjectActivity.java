package com.zerokol.kanban.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.zerokol.kanban.R;
import com.zerokol.kanban.daos.ProjectDAO;
import com.zerokol.kanban.models.Project;
import com.zerokol.kanban.utils.KanbanHelper;

public class CreateProjectActivity extends ActionBarActivity {
	private EditText name;
	private Button save;
	private ProjectDAO projectDataAccessObject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_project);

		name = (EditText) findViewById(R.id.create_project_description_editText);
		save = (Button) findViewById(R.id.create_project_save_button);

		projectDataAccessObject = new ProjectDAO(this);

		save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Project project = new Project();
				project.setName(name.getText().toString());
				project.setCreatedAt(KanbanHelper.getTimeCurrent());

				projectDataAccessObject.open();
				projectDataAccessObject.create(project);
				projectDataAccessObject.close();

				Intent intent = new Intent();
				setResult(Activity.RESULT_OK, intent);
				finish();
			}
		});
	}
}
