import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.rest.client.api.IGenericClient;
import ca.uhn.fhir.rest.client.interceptor.LoggingInterceptor;

import java.io.IOException;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Patient;

public class SampleClient {
	
	static final String LAST_NAME_FILE_PATH = "\\src\\main\\resources\\last_names.txt";
	
    public static void main(String[] theArgs) throws IOException {

        // Create a FHIR client
        FhirContext fhirContext = FhirContext.forR4();
        IGenericClient client = fhirContext.newRestfulGenericClient("http://hapi.fhir.org/baseR4");
        client.registerInterceptor(new LoggingInterceptor(false));
        
        LastNameFileReader last_name_reader = new LastNameFileReader(LAST_NAME_FILE_PATH);
        
        for (int i = 0; i < 3; i++) {
        	int number_of_patients = 0;
        	int total_search_time = 0;
        	String last_name = last_name_reader.readLastName();
        	while (last_name != null) {
        		// Search for Patient resources
                Bundle response = client
                        .search()
                        .forResource("Patient")
                        .where(Patient.FAMILY.matches().value(last_name))
                        .returnBundle(Bundle.class)
                        .execute();
                
                total_search_time++;
                last_name = last_name_reader.readLastName();
            }
        	int average_response_time = total_search_time/number_of_patients;
        	System.out.print("Average Response Time: " + average_response_time);
        	last_name_reader.reset();
        }
    	last_name_reader.close();
    }
       
}
