package seedu.duke.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.duke.module.Module;

public class Storage {
    private static final String filePath = "data/duke.txt";

    public List<Module> load() throws IOException {

        File file = new File(filePath);

        file.getParentFile().mkdirs();

        if (!file.exists()) {
            file.createNewFile();
        }

        List<Module> modules = new ArrayList<>();

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();

            String[] parts = line.split("\\|");

            String code = parts[0];
            int mc = Integer.parseInt(parts[1]);

            Module module = new Module(code, mc);
            module.markCompleted();

            modules.add(module);
        }
        scanner.close();
        return modules;
    }
    public static void save(List<Module> modules) throws IOException {

        FileWriter writer = new FileWriter(filePath);

        for (Module module : modules) {
            writer.write(module.getModuleCode() + "|" +
                    module.getModularCredits());
            writer.write("\n");
        }

        writer.close();
    }
}
