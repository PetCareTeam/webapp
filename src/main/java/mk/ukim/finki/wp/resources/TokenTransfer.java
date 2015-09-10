package mk.ukim.finki.wp.resources;

public class TokenTransfer
{

	private final String token;
	private final Long id;


	public TokenTransfer(String token,Long id)
	{
		this.token = token;
		this.id=id;
	}


	public Long getId() {
		return id;
	}


	public String getToken()
	{
		return this.token;
	}

}