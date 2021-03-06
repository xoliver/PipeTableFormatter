package pipetableformatter.plugin;

import com.intellij.openapi.editor.Editor;
import pipetableformatter.operation.Format;

import static pipetableformatter.FormatOptions.formatOptions;

public class PipeTableFormatPreservingOuterStateAction extends AbstractPipeTableFormatAction {

    public PipeTableFormatPreservingOuterStateAction() {
        super(new PipeTableActionHandler() {
            @Override
            protected Runnable action(Editor editor) {
                return new Format(new IdeaPipeTableEditor(editor), formatOptions().preserveOuterState());
            }
        });
    }

}
