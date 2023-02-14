package controllers;

import dto.SavedVoteDTO;
import dto.VoteDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.api.ISenderService;
import service.api.IVoteService;
@RestController
@RequestMapping("/vote")
public class VoteController {
    private final IVoteService voteService;
    private final ISenderService senderService;

    public VoteController(IVoteService voteService, ISenderService senderService) {
        this.voteService = voteService;
        this.senderService = senderService;
    }

    @PostMapping()
    public void vote(@RequestBody VoteDTO voteDTO) {
        voteService.validate(voteDTO);
        SavedVoteDTO savedVote = new SavedVoteDTO(voteDTO);
        voteService.save(savedVote);
        //senderService.sendVoteConfirmation(savedVote);
    }
}