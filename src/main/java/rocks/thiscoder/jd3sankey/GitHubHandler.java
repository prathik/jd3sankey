package rocks.thiscoder.jd3sankey;

import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.service.GistService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GitHubHandler {
    private Gist gist;
    private final GistService gistService;
    private final Map<String, GistFile> gistFileMap = new HashMap<>();

    public GitHubHandler(String username, String password, String description) {
        this.gistService = new GistService();
        gistService.getClient().setCredentials(username, password);
        this.gist = new Gist();
        gist.setDescription(description);
        gist.setPublic(true);
    }

    public void addFile(String name, String content) {
        GistFile file = new GistFile();
        file.setContent(content);
        gistFileMap.put(name, file);
    }

    public Gist publish() throws IOException {
        gist.setFiles(gistFileMap);
        gist = gistService.createGist(gist);
        return gist;
    }
}
