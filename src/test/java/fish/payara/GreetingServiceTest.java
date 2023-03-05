package fish.payara;

import lombok.extern.java.Log;
import me.hikingcarrot7.moodleripoff.examples.ConfigPropertyProvider;
import me.hikingcarrot7.moodleripoff.examples.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@Log
@ExtendWith(MockitoExtension.class)
class GreetingServiceTest {

  @InjectMocks
  protected GreetingService greetingService;

  @Mock
  protected ConfigPropertyProvider propertyProvider;


  @BeforeEach
  void init() {
    when(propertyProvider.getApplicationServer()).thenReturn("payara-6-community");
    when(propertyProvider.getJakartaVersion()).thenReturn("10.0.0");
  }

  @Test
  void testGreetingService() {

    var jsonObject = greetingService.greet("John");
    log.log(Level.INFO, jsonObject::toString);


    assertEquals("Hello there John", jsonObject.getString("greeting"));
    assertEquals("Getting started with Jakarta EE!", jsonObject.getString("message"));
    assertEquals("Jakarta EE", jsonObject.getString("platform"));
    assertEquals("10.0.0", jsonObject.getString("platformVersion"));
    assertEquals("payara-6-community", jsonObject.getString("implementation"));
    assertNotNull(jsonObject.get("date"));

  }
}
