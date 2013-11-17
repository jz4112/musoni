package localStore;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import android.content.Context;
import forms.Form;

public class LocalStore implements Serializable {
	private static final long serialVersionUID = 42L;
	private static String dirPathName = "." + File.separator + "form lists";
	private static File dir;
	private static List<Form> forms;
	private static LocalStore instance = null;
	private static Context context;

	private LocalStore(Context context) {
		forms = new LinkedList<Form>();
		dir = new File(dirPathName);
		do {
			dir.mkdirs();
		} while (!dir.isDirectory());

		this.context = context;
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
		File outputFile = new File(dir, filePath(newForm));

		OutputStream buffer;

		buffer = new BufferedOutputStream(new FileOutputStream(outputFile));

		ObjectOutput output = new ObjectOutputStream(buffer);
		output.writeObject(newForm);

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
		for (File f : dir.listFiles()) {
			buffer = new BufferedInputStream(new FileInputStream(f));
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
