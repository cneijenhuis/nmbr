package com.baseapp.eyeem.androidsdk.query;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;


public class EyeemUsersQuery extends EyeEmAbstractQuery {

	public enum EESpecificUsersEndpoint {
	    EEspecificUsersEndpointPhotos,
	    EEspecificUsersEndpointLikedPhotos,
	    EEspecificUsersEndpointFriendsPhotos,
	    EEspecificUsersEndpointLikedAlbums,
	    EEspecificUsersEndpointFeed,
	    EEspecificUsersEndpointDiscover,
	    EEspecificUsersEndpointFriends,
	    EEspecificUsersEndpointFollowers,
	    EEspecificUsersEndpointTopics,
	    EEspecificUsersEndpointSocialMedia,
	    EEspecificUsersEndpointNewsSettings,
	    EEspecificUsersEndpointShare,
	    EEspecificUsersEndpointHide,
	    EEspecificUsersEndpointNothing
	};
	public EESpecificUsersEndpoint specificEndpoint = EESpecificUsersEndpoint.EEspecificUsersEndpointNothing;
	public boolean suggested;
	public String closestVenueFsIds;
	public String cityName;
	public boolean upload;
	public boolean photoLike;
	public boolean photoComment;
	public boolean albumLike;
	public boolean userFollow;
	public boolean timelinePopup;
	public String fullname;
	public String nickname;
	public String username;
	public String password;
	public String description;
	public boolean emailNotification;
	public boolean pushNotification;
	public boolean facebookTimeline;
	public String friend_id;
	public boolean keys;
	public String oauth_token;
	public String oauth_token_secret;
	public int service_user_id = -1;
	public String service_screen_name;
	public boolean follow;
	public boolean callback;
	public boolean connect;
	
	private boolean suggestedFlag = false;
	private boolean uploadFlag = false;
	private boolean photoLikeFlag = false;
	private boolean photoCommentFlag = false;
	private boolean albumLikeFlag = false;
	private boolean userFollowFlag = false;
	private boolean timelinePopupFlag = false;
	private boolean emailNotificationFlag = false;
	private boolean pushNotificationFlag = false;
	private boolean facebookTimelineFlag = false;
	private boolean keysFlag = false;
	private boolean followFlag = false;
	private boolean callbackFlag = false;
	private boolean connectFlag = false;
	
	public void setSuggested(boolean suggested){
		this.suggested = suggested;
		suggestedFlag = true;
	}
	public void setPhotoLike(boolean photoLike){
		this.photoLike = photoLike;
		photoLikeFlag = true;
	}
	public void setUpload(boolean upload){
		this.upload = upload;
		uploadFlag = true;
	}
	public void setPhotoComment(boolean photoComment){
		this.photoComment = photoComment;
		photoCommentFlag = true;
	}
	public void setAlbumLike(boolean albumLike){
		this.albumLike = albumLike;
		albumLikeFlag = true;
	}
	public void setUserFollow(boolean userFollow){
		this.userFollow = userFollow;
		userFollowFlag = true;
	}
	public void setTimelinePopup(boolean timelinePopup){
		this.timelinePopup = timelinePopup;
		timelinePopupFlag = true;
	}
	public void setEmailNotification(boolean emailNotification){
		this.emailNotification = emailNotification;
		emailNotificationFlag = true;
	}
	public void setPushNotification(boolean pushNotification){
		this.pushNotification = pushNotification;
		pushNotificationFlag = true;
	}
	public void setFacebookTimeline(boolean facebookTimeline){
		this.facebookTimeline = facebookTimeline;
		facebookTimelineFlag = true;
	}
	public void setKeys(boolean keys){
		this.keys = keys;
		keysFlag = true;
	}
	public void setFollow(boolean follow){
		this.follow = follow;
		followFlag = true;
	}
	public void setCallback(boolean callback){
		this.callback = callback;
		callbackFlag = true;
	}
	public void setConnect(boolean connect){
		this.connect = connect;
		connectFlag = true;
	}
	
	
	public ArrayList<NameValuePair> transformQuery(){
		generalEndpoint = URL_USERS;
		if(specificEndpoint != EESpecificUsersEndpoint.EEspecificUsersEndpointNothing){
			switch(specificEndpoint){
				case EEspecificUsersEndpointDiscover:
					specificEndpointString = URL_DISCOVER;
					break;
				case EEspecificUsersEndpointFeed:
					specificEndpointString = URL_FEED;
					break;
				case EEspecificUsersEndpointFollowers:
					specificEndpointString = URL_USERS_FOLLOWERS;
					break;
				case EEspecificUsersEndpointFriends:
					specificEndpointString = URL_USERS_FRIENDS;
					break;
				case EEspecificUsersEndpointFriendsPhotos:
					specificEndpointString = URL_USERS_FRIENDS_PHOTOS;
					break;
				case EEspecificUsersEndpointHide:
					specificEndpointString = URL_HIDE;
					break;
				case EEspecificUsersEndpointLikedAlbums:
					specificEndpointString = URL_USERS_LIKEDALBUMS;
					break;
				case EEspecificUsersEndpointLikedPhotos:
					specificEndpointString = URL_USERS_LIKEDPHOTOS;
					break;
				case EEspecificUsersEndpointNewsSettings:
					specificEndpointString = URL_USERS_NEWS_SETTINGS;
					break;
				case EEspecificUsersEndpointPhotos:
					specificEndpointString = URL_PHOTOS;
					break;
				case EEspecificUsersEndpointShare:
					specificEndpointString = URL_SHARE;
					break;
				case EEspecificUsersEndpointSocialMedia:
					specificEndpointString = URL_USERS_SOCIAL_MEDIA;
					break;
				case EEspecificUsersEndpointTopics:
					specificEndpointString = URL_TOPICS;
					break;

				default:
					break;
					
			}
		}

		
		
		ArrayList<NameValuePair> parameters = super.transformQuery();
		
		if(suggestedFlag){
			parameters.add(new BasicNameValuePair("suggested", String.valueOf(suggested)));
		}
		if(closestVenueFsIds != null){
			parameters.add(new BasicNameValuePair("closestVenueFsIds", closestVenueFsIds));			
		}
		if(cityName != null){
			parameters.add(new BasicNameValuePair("cityName", cityName));
		}
		if(uploadFlag){
			parameters.add(new BasicNameValuePair("upload", String.valueOf(upload)));
		}
		if(photoLikeFlag){
			parameters.add(new BasicNameValuePair("photoLike", String.valueOf(photoLike)));
		}
		if(photoCommentFlag){
			parameters.add(new BasicNameValuePair("photoComment", String.valueOf(photoComment)));
		}
		if(albumLikeFlag){
			parameters.add(new BasicNameValuePair("albumLike", String.valueOf(albumLike)));
		}
		if(userFollowFlag){
			parameters.add(new BasicNameValuePair("userFollow", String.valueOf(userFollow)));
		}
		if(timelinePopupFlag){
			parameters.add(new BasicNameValuePair("timelinePopup", String.valueOf(timelinePopup)));
		}
		if(fullname != null){
			parameters.add(new BasicNameValuePair("fullname", fullname));
		}
		if(nickname != null){
			parameters.add(new BasicNameValuePair("nickname", nickname));
		}
		if(username != null){
			parameters.add(new BasicNameValuePair("username", username));
		}
		if(password != null){
			parameters.add(new BasicNameValuePair("password", password));
		}
		if(description != null){
			parameters.add(new BasicNameValuePair("description", description));
		}
		if(emailNotificationFlag){
			parameters.add(new BasicNameValuePair("emailNotification", String.valueOf(emailNotification)));
		}
		if(pushNotificationFlag){
			parameters.add(new BasicNameValuePair("pushNotification", String.valueOf(pushNotification)));
		}
		if(facebookTimelineFlag){
			parameters.add(new BasicNameValuePair("facebookTimeline", String.valueOf(facebookTimeline)));
		}
		if(friend_id != null){
			parameters.add(new BasicNameValuePair("friend_id", friend_id));
		}
		if(keysFlag){
			parameters.add(new BasicNameValuePair("keys", String.valueOf(keys)));
		}
		if(oauth_token != null){
			parameters.add(new BasicNameValuePair("oauth_token", oauth_token));
		}
		if(oauth_token_secret != null){
			parameters.add(new BasicNameValuePair("oauth_token_secret", oauth_token_secret));
		}
		if(service_user_id > 0){
			parameters.add(new BasicNameValuePair("service_user_id", String.valueOf(service_user_id)));
		}
		if(service_screen_name != null){
			parameters.add(new BasicNameValuePair("service_screen_name", service_screen_name));
		}
		if(followFlag){
			parameters.add(new BasicNameValuePair("follow", String.valueOf(follow)));
		}
		if(callbackFlag){
			parameters.add(new BasicNameValuePair("callback", String.valueOf(callback)));
		}
		if(connectFlag){
			parameters.add(new BasicNameValuePair("connect", String.valueOf(connect)));
		}
		
		
		return parameters;
		
		
		
		
		
		
	}
}
