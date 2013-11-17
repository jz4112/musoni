package localStore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import forms.Form;

public class LocalStore {
	private static List<Form> forms;
	private static LocalStore instance = null;
	private static Context context;

	private LocalStore(Context context) {
		forms = new LinkedList<Form>();
		LocalStore.context = context;
	}

	public static void setUp(Context context) {
	  instance = new LocalStore(context);
	}

	public static LocalStore getInstance(Context context) {
	  if(instance == null) {
	    instance = new LocalStore(context);
	  }
	  return instance;
	}

	public static LocalStore getInstance() {
	  if(instance == null && context == null) {
	    throw new RuntimeException("No context supplied!");
	  } else if (instance == null) {
	    instance = new LocalStore(context);
	  }
	  return instance;
	}

	private String filePath(Form form) {
		return "Form #" + forms.indexOf(form) + ".pdf";
	}
/**
 * 	every time we add a new form, we automatically save it to the directory
 * @param newForm
 * @throws Exception
 */
	public void add(Form newForm) throws Exception{

		forms.add(newForm);
		// create a new file
		OutputStream buffer;

		FileOutputStream fos = context.openFileOutput(filePath(newForm), Context.MODE_PRIVATE);
		buffer = new BufferedOutputStream(fos);
		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(newForm);
		output.flush();
		output.close();
	}

	/**
	 * convert all files back to forms every time we restart the app
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean loadAll() throws ClassNotFoundException, IOException {
		InputStream buffer;
		for (String fileName : context.fileList()) {
		  FileInputStream fis = context.openFileInput(fileName);
			buffer = new BufferedInputStream(fis);
			ObjectInput in = new ObjectInputStream(buffer);
			Form form = (Form)in.readObject();
			forms.add(form);
			in.close();
		}
		return true;
	}

	public List<Form> getForms() {
	  return forms;
	}
}
