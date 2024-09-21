import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

public class TriggerJenkinsJob {
    public static void main(String[] args) {
        try {
            // Jenkins credentials and job details
            String jenkinsUrl = "http://your-jenkins-url.com/job/your-job-name/build";  // Replace with your Jenkins URL
            String username = "your-username";  // Replace with your Jenkins username
            String apiToken = "your-api-token";  // Replace with your Jenkins API token

            // Create the URL object
            URL url = new URL(jenkinsUrl);

            // Open connection
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);

            // Add basic authentication header
            String authStr = username + ":" + apiToken;
            String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
            connection.setRequestProperty("Authorization", "Basic " + authEncoded);

            // Send the request
            OutputStream os = connection.getOutputStream();
            os.flush();
            os.close();

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                System.out.println("Build triggered successfully!");
            } else {
                System.out.println("Failed to trigger build. Response code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
