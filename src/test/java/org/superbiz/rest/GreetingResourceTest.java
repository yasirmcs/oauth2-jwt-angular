/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.superbiz.rest;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import javax.ws.rs.core.MediaType;
import org.apache.cxf.jaxrs.client.WebClient;
import org.apache.openejb.jee.WebApp;
import org.apache.openejb.junit.ApplicationComposer;
import org.apache.openejb.testing.Classes;
import org.apache.openejb.testing.EnableServices;
import org.apache.openejb.testing.Module;
import org.junit.Test;
import org.junit.runner.RunWith;

@EnableServices(value = "jaxrs", httpDebug = true)
@RunWith(ApplicationComposer.class)
public class GreetingResourceTest {

    @Module
    @Classes(GreetingResource.class)
    public WebApp app() {
        return new WebApp().contextRoot("test");
    }

    @Test
    public void get() throws IOException {
        final String message = WebClient.create("http://localhost:4204")
                .path("/test/greeting/")
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        assertEquals("Hi MicroProfile JWT!", message);
    }

    @Test
    public void post() throws IOException {
        final String message = WebClient.create("http://localhost:4204")
                .path("/test/greeting/")
                .type(MediaType.APPLICATION_JSON_TYPE)
                .accept(MediaType.APPLICATION_JSON_TYPE)
                .post("Hi REST!", String.class);
        assertEquals("hi rest!", message);
    }

}
