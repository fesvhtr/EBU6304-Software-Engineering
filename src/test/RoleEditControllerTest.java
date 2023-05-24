package test;
import controller.RoleEditController;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

public class RoleEditControllerTest {

    @BeforeClass
    public static void initToolkit() {
        if (Boolean.FALSE.equals(java.awt.GraphicsEnvironment.isHeadless())) {
            new JFXPanel(); // 初始化 JavaFX Toolkit
        }
    }

    @Test
    public void testCloseMethod() {
        // 创建一个测试的 MouseEvent
        MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, null, 0, false, false, false, false, false, false, false, false, false, false, null);

        // 创建被测试的对象
        RoleEditController roleEditController = new RoleEditController();

        // 在 JavaFX 应用程序线程上运行测试代码
        Platform.runLater(() -> {
            // 创建一个舞台对象
            Stage stage = new Stage();
            // 创建一个测试用的按钮和舞台对象
            Button exitButton = new Button();
            // 创建一个场景并将按钮添加到场景中
            Scene scene = new Scene(exitButton);

            // 将场景设置给舞台
            stage.setScene(scene);

            // 调用被测试的方法
            roleEditController.close(event);

            // 验证舞台是否被关闭
            assertFalse(stage.isShowing());
        });
    }
}
