package applications;

import interfaces.IDeploy;

public class DesktopApp extends Application {
    public final static String ENVIRONMENTS = "linux,mac,windows";

    public DesktopApp(String name) {
        super(name, ENVIRONMENTS);
    }

    public DesktopApp(String name, String supportedEnvironments) {
        super(name, supportedEnvironments);
    }

    @Override
    public void deploy() {
        System.out.println("asd");
    }

    @Override
    public String toString() {
        return "- Application type: " + this.getClass().getSimpleName() + "\n" +
                "- Name: " + this.getName() + "\n" +
                "- Platforms: " + this.getSupportedEnvironments();
    }
}
