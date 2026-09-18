package io.lexi115.sparxie.logger.warps;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warps")
@RequiredArgsConstructor
public class WarpController {
    private final WarpService warpService;
}
