package DefaultPackage;

import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;

public class MyMapWritable extends MapWritable {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (@SuppressWarnings("rawtypes") Entry entry : entrySet()) {
            sb.append(((Text)entry.getKey()).toString()).append(":").append(entry.getValue().toString()).append(",");
        }
        if (size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        return sb.toString();
    }
}
